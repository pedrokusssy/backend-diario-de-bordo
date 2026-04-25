package pt.diariobordo.diario.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/updates")
public class NotificationController {

    // Mapa Inteligente: ID do Formando -> Lista de Conexões (Suporta múltiplas abas/dispositivos por pessoa)
    private final Map<UUID, CopyOnWriteArrayList<SseEmitter>> emitters = new ConcurrentHashMap<>();

    // Agora o Frontend tem de enviar o pessoaId na URL para se subscrever
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam UUID pessoaId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        // Se este utilizador ainda não tiver uma lista de conexões, cria uma nova
        emitters.computeIfAbsent(pessoaId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        // Limpeza automática de memória quando a aba é fechada ou a ligação cai
        emitter.onCompletion(() -> removeEmitter(pessoaId, emitter));
        emitter.onTimeout(() -> removeEmitter(pessoaId, emitter));
        emitter.onError((e) -> removeEmitter(pessoaId, emitter));

        return emitter;
    }

    // Função auxiliar para manter a RAM limpa
    private void removeEmitter(UUID pessoaId, SseEmitter emitter) {
        CopyOnWriteArrayList<SseEmitter> userEmitters = emitters.get(pessoaId);
        if (userEmitters != null) {
            userEmitters.remove(emitter);
            // Se o utilizador já não tem nenhuma aba aberta, limpa-o do mapa principal
            if (userEmitters.isEmpty()) {
                emitters.remove(pessoaId);
            }
        }
    }

    // --- O NOVO MÉTODO DIRECIONADO ---
    // O teu DiarioService agora vai chamar este método passando quem fez a alteração e o que alterou.
    public void notifyUser(UUID pessoaId, String message) {
        CopyOnWriteArrayList<SseEmitter> userEmitters = emitters.get(pessoaId);

        if (userEmitters != null) {
            for (SseEmitter emitter : userEmitters) {
                try {
                    // Manda o sinal só para as abas deste utilizador específico
                    emitter.send(SseEmitter.event().name("db-change").data(message));
                } catch (IOException e) {
                    removeEmitter(pessoaId, emitter);
                }
            }
        }
    }
}
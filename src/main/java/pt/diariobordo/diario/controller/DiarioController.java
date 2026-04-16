package pt.diariobordo.diario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.DiarioDtoRequest;
import pt.diariobordo.diario.entity.Diario;
import pt.diariobordo.diario.service.DiarioService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/diario")
public class DiarioController {

    private final DiarioService diarioService;
    public DiarioController(DiarioService diarioService) {
        this.diarioService = diarioService;
    }

    @GetMapping
    public List<Diario> listar(){
       return this.diarioService.getDiarios();
    }

    @GetMapping("/f/{id}")
    public List<Diario> buscarPorFormandoId(@PathVariable UUID id){
       return this.diarioService.diarioPorFormandoId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diario> buscarPorDiarioId(@PathVariable UUID id) {

        Optional<Diario> diarioOptional = this.diarioService.dairoById(id);

        // Verifica se o diário existe dentro do Optional
        if (diarioOptional.isPresent()) {
            // Se existe, pega o objeto real (.get()) e retorna 200 OK
            return ResponseEntity.ok(diarioOptional.get());
        } else {
            // Se não existe, retorna o erro 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void novoDiario(@RequestBody DiarioDtoRequest diarioDtoRequest){
        this.diarioService.novoDiario(diarioDtoRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diario> editarDiario(
            @PathVariable UUID id,
            @RequestBody DiarioDtoRequest diarioDtoRequest) {

        // 1. Você envia o ID e os novos dados para a sua camada de Service
        Diario diarioAtualizado = this.diarioService.atualizarDiario(id, diarioDtoRequest);

        // 2. Retorna o status 200 OK junto com o diário atualizado
        return ResponseEntity.ok(diarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiarioPorId(@PathVariable UUID id) {

        // Aqui você chama o seu service para deletar no banco de dados
        this.diarioService.deletarDiario(id);

        // Retorna o status 204 (No Content), indicando que deu tudo certo
        // e não há conteúdo para retornar no corpo da resposta.
        return ResponseEntity.noContent().build();
    }

}

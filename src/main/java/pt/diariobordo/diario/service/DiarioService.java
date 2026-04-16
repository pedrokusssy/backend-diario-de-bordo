package pt.diariobordo.diario.service;

import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.ActidadeDtoReponse;
import pt.diariobordo.diario.dto.DiarioDtoRequest;
import pt.diariobordo.diario.dto.DiarioDtoResponse;
import pt.diariobordo.diario.entity.Actividade;
import pt.diariobordo.diario.entity.Diario;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.repository.ActividadeRepositorry;
import pt.diariobordo.diario.repository.DiarioRepository;
import pt.diariobordo.diario.repository.FormandoRepository;

import java.util.*;

@Service
public class DiarioService {
    private final DiarioRepository diarioRepository;
    private final FormandoRepository formandoRepository;
    private final ActividadeRepositorry actividadeRepositorry;

    public  DiarioService(DiarioRepository diarioRepository, FormandoRepository formandoRepository, ActividadeRepositorry actividadeRepositorry) {
        this.diarioRepository = diarioRepository;
        this.formandoRepository = formandoRepository;
        this.actividadeRepositorry = actividadeRepositorry;
    }

    public List<Diario> getDiarios() {
       return this.diarioRepository.findAll();
    }

    public List<Diario> diarioPorFormandoId(UUID id) {
       return this.diarioRepository.findDiarioByFormandoId(id);

    }

    public Optional<Diario> dairoById(UUID id) {
        Optional<Diario> diario = this.diarioRepository.findById(id);
        return diario;
    }

    public Diario atualizarDiario(UUID id, DiarioDtoRequest dto) {
        // 1. Busca o Diário
        Diario diario = diarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diário não encontrado"));

        // 2. Busca a Atividade e o Formando usando os IDs que vieram do Front
        Actividade actividade = this.actividadeRepositorry.findById(dto.actividade_id())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        Formando formando = formandoRepository.findById(dto.formando_id())
                .orElseThrow(() -> new RuntimeException("Formando não encontrado"));

        // 3. Atualiza os dados
        diario.setDataActividade(dto.dataActividade());
        diario.setDescricao(dto.descricao());
        diario.setActividade(actividade); // Agora você está passando o OBJETO
        diario.setFormando(formando);     // Agora você está passando o OBJETO

        return diarioRepository.save(diario);
    }

    public Diario novoDiario(DiarioDtoRequest diarioDtoRequest) {
        System.out.println("passou aqui: 2 : entrou ");
        Optional<Formando> formando = this.formandoRepository.findById(diarioDtoRequest.formando_id());
        System.out.println("passou aqui: 3: já buscou formando");
        Optional<Actividade> actividade = this.actividadeRepositorry.findById(diarioDtoRequest.actividade_id());
        System.out.println("passou aqui: 4: já buscou Activdade");
        Diario diario = new Diario(formando.orElse(null), actividade.orElse(null), diarioDtoRequest.descricao(), diarioDtoRequest.dataActividade());
            System.out.println("passou aqui: 4: já criou diario");
        return this.diarioRepository.save(diario);
    }

    public void deletarDiario(UUID id) {
        this.diarioRepository.deleteById(id);
    }
}

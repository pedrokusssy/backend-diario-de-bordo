package pt.diariobordo.diario.service;

import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.FormacaoDtoRequest;
import pt.diariobordo.diario.dto.FormacaoDtoResponse;
import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;
import pt.diariobordo.diario.repository.FormacaoRepository;
import pt.diariobordo.diario.repository.PeriodoRepository;
import pt.diariobordo.diario.repository.TutorRepository;

import java.util.*;

@Service
public class FormacoService {

    private final FormacaoRepository formacaoRepository;
    private final TutorRepository tutorRepository;
    private final PeriodoRepository periodoRepository;

    public FormacoService(FormacaoRepository formacaoRepository, TutorRepository tutorRepository, PeriodoRepository periodoRepository) {
        this.formacaoRepository = formacaoRepository;
        this.tutorRepository = tutorRepository;
        this.periodoRepository = periodoRepository;
    }

    public List<Formacao> listaFormacoes(){
        System.out.println("passou aqui: 2 : entrou ");
        return this.formacaoRepository.findAll();
    }

    public List<FormacaoDtoResponse> getAll() {
        return this.formacaoRepository.findAll().stream().map(FormacaoDtoResponse::new).toList();
    }


    public Formacao novaFormacao(FormacaoDtoRequest formacaoDtoRequest) {
        System.out.println("formacaoDtoRequest - Entrou 2"+formacaoDtoRequest);
        //buscar periodo
        Optional<Periodo> periodo = this.periodoRepository.findById(formacaoDtoRequest.periodo());
        System.out.println("formacaoDtoRequest - Entrou 3"+periodo);

        System.out.println("formacaoDtoRequest - Entrou 5");
        Formacao formacao = new Formacao(formacaoDtoRequest.titulo(), formacaoDtoRequest.descricao(), periodo.orElse(null));
        this.formacaoRepository.save(formacao);
        return formacao;
    }

    // Retorna as formações de um formando
    public Set<Formacao> getFormacoesPorFormandoId(UUID formandoId) {
        return this.formacaoRepository.findAllByFormandosId(formandoId);
    }

}

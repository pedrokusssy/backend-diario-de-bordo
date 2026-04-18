package pt.diariobordo.diario.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.FormacaoDtoRequest;
import pt.diariobordo.diario.dto.FormacaoDtoResponse;
import pt.diariobordo.diario.dto.FormandoIdRequest;
import pt.diariobordo.diario.dto.GetFormacoesPorFormandoIdDtoRequest;
import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;
import pt.diariobordo.diario.repository.FormacaoRepository;
import pt.diariobordo.diario.repository.FormandoRepository;
import pt.diariobordo.diario.repository.PeriodoRepository;
import pt.diariobordo.diario.repository.TutorRepository;

import java.util.*;

@Service
public class FormacoService {

    private final FormacaoRepository formacaoRepository;
    private final TutorRepository tutorRepository;
    private final PeriodoRepository periodoRepository;
    private final FormandoRepository formandoRepository;

    public FormacoService(FormacaoRepository formacaoRepository, TutorRepository tutorRepository, PeriodoRepository periodoRepository, FormandoRepository formandoRepositoty) {
        this.formacaoRepository = formacaoRepository;
        this.tutorRepository = tutorRepository;
        this.periodoRepository = periodoRepository;
        this.formandoRepository = formandoRepositoty;
    }

    public List<Formacao> listaFormacoes(){
        System.out.println("passou aqui: 2 : entrou ");
        return this.formacaoRepository.findAll();
    }

    public List<FormacaoDtoResponse> getAll() {
        return this.formacaoRepository.findAll().stream().map(FormacaoDtoResponse::new).toList();
    }


    @Transactional
    public Formacao novaFormacao(FormacaoDtoRequest request) {
        // 1. Busca as referências REAIS do banco

        Tutor tutor = tutorRepository.findById(request.tutor().getId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        Periodo periodo = periodoRepository.findById(request.periodo().getId())
                .orElseThrow(() -> new RuntimeException("Período não encontrado"));

        // 2. Cria a nova formação associando os objetos acima
        Formacao formacao = new Formacao(
                request.titulo(),
                request.descricao(),
                periodo,
                tutor
        );

        // 3. Salva
        return formacaoRepository.save(formacao);
    }

    // Retorna as formações de um formando
    public List<GetFormacoesPorFormandoIdDtoRequest> getFormacoesPorFormandoId(UUID formandoId) {
        return this.formacaoRepository.findAllByFormandosId(formandoId).stream().map(GetFormacoesPorFormandoIdDtoRequest::new).toList();
    }

    public void setFormandoAtFormacao(UUID formacaoId, FormandoIdRequest formandoId) {
        Formacao formacao = formacaoRepository.findById(formacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Formação não encontrada"));

        Formando formando = formandoRepository.findById(formandoId.formandoId())
                .orElseThrow(() -> new EntityNotFoundException("Formando não encontrado"));

        // Adiciona o formando à lista (o JPA cuida da tabela intermediária)
        formacao.getFormandos().add(formando);

        formacaoRepository.save(formacao);

    }

}

package pt.diariobordo.diario.service;

import org.springframework.stereotype.Service;

import pt.diariobordo.diario.dto.FormandoDtoRequest;
import pt.diariobordo.diario.dto.FormandoDtoResponde;

import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.repository.FormacaoRepository;
import pt.diariobordo.diario.repository.FormandoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormandoService {
    private final FormandoRepository formandoRepository;
    private final FormacaoRepository formacaoRepository;

    public FormandoService(FormandoRepository formandoRepository,  FormacaoRepository formacaoRepository) {
        this.formandoRepository = formandoRepository;
        this.formacaoRepository = formacaoRepository;
    }

    public Formando novoFormando(FormandoDtoRequest formandoDtoRequest) {
        Formando formando = new Formando(formandoDtoRequest.nome(), formandoDtoRequest.hospitalOrigem());

        return this.formandoRepository.save(formando);
    }

    public List<FormandoDtoResponde> getAll() {
        return this.formandoRepository.findAll().stream().map(FormandoDtoResponde::new).toList();
    }

    public Optional<Formando> getFormando(UUID id) {

        Optional<Formando> formando =  this.formandoRepository.findById(id);

        return formando;
    }

}

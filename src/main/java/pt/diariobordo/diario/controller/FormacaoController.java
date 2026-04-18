package pt.diariobordo.diario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.*;
import pt.diariobordo.diario.entity.Diario;
import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.repository.FormacaoRepository;
import pt.diariobordo.diario.service.FormacoService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/formacao")
public class FormacaoController {

    private final FormacoService formacaoService;
    private final FormacaoRepository formacaoRepository;
    public FormacaoController(FormacoService formacaoService,  FormacaoRepository formacaoRepository) {
        this.formacaoService = formacaoService;
        this.formacaoRepository = formacaoRepository;
    }

    @GetMapping
    public List<FormacaoDtoResponse> formacoaList(){
        return this.formacaoRepository.findAll().stream().map(FormacaoDtoResponse::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetFormacoesPorFormandoIdDtoRequest>> formacaoByIdFormando(@PathVariable UUID id){

        // 2. Retorna o status 200 OK
        return ResponseEntity.ok(this.formacaoService.getFormacoesPorFormandoId(id));
    }

    @PostMapping
    public Formacao newFormacao(@RequestBody FormacaoDtoRequest formacaoDtoRequest){
        System.out.println("formacaoDtoRequest - Entrou 1"+formacaoDtoRequest);
        return this.formacaoService.novaFormacao(formacaoDtoRequest);
    }

    @PostMapping("/{formacoId}/formando")
    public void formand(@PathVariable UUID formacoId, @RequestBody FormandoIdRequest formandoId){

        this.formacaoService.setFormandoAtFormacao( formacoId, formandoId);
    }


}

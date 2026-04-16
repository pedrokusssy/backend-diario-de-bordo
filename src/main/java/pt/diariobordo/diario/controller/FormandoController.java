package pt.diariobordo.diario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.FormandoDtoRequest;
import pt.diariobordo.diario.dto.FormandoDtoResponde;
import pt.diariobordo.diario.entity.Diario;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.service.FormandoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/formando")
public class FormandoController {

    private final FormandoService formandoService;

    public FormandoController(FormandoService formandoService) {
        this.formandoService = formandoService;
    }

    @GetMapping
    public List<FormandoDtoResponde> formandos(){
       return this.formandoService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Formando> formandoById(@PathVariable UUID id) {

        Optional<Formando> formando = this.formandoService.getFormando(id);
        // Verifica se o diário existe dentro do Optional
        // Se existe, pega o objeto real (.get()) e retorna 200 OK
        // Se não existe, retorna o erro 404 Not Found
        return formando.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void formando(@RequestBody FormandoDtoRequest formandoDtoRequest){
        System.out.println(formandoDtoRequest);
        this.formandoService.novoFormando(formandoDtoRequest);
    }



}

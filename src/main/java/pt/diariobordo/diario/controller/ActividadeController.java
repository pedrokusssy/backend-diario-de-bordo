package pt.diariobordo.diario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.ActidadeDtoReponse;
import pt.diariobordo.diario.dto.ActidadeDtoRequest;
import pt.diariobordo.diario.service.ActividadeService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/actividade")
public class ActividadeController {

    private final ActividadeService actividadeService;
    public ActividadeController(ActividadeService actividadeService) {
        this.actividadeService = actividadeService;
    }

    @GetMapping
    public List<ActidadeDtoReponse> getActividades () {
        return this.actividadeService.getAllActivadade();
    }

    @PostMapping
    public void newActividade (@RequestBody ActidadeDtoRequest actidadeDtoRequest) {
        this.actividadeService.novaActividade(actidadeDtoRequest);
    }

}

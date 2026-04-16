package pt.diariobordo.diario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.PeriodoDtoResponse;
import pt.diariobordo.diario.dto.PeriodoDtoResquest;
import pt.diariobordo.diario.service.PeriodoService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/periodo")
public class ControllerPeriodo {

    private final PeriodoService periodoService;

    public ControllerPeriodo(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping
    public List<PeriodoDtoResponse> listaPeriodos(){
        return this.periodoService.listaPeriodos();
    }

    @PostMapping
    public void   inserePeriodo(@RequestBody PeriodoDtoResquest request){
        this.periodoService.novoPeriodo(request);
    }

}

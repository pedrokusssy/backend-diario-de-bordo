package pt.diariobordo.diario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.diariobordo.diario.dto.TutorDtoRequest;
import pt.diariobordo.diario.entity.Tutor;

import pt.diariobordo.diario.service.TutorService;

import java.util.List;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/tutor")
public class TutorController {

    private TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public List<Tutor> getTutorDto() {
       return this.tutorService.getAllTutor();
    }

    @GetMapping("/{id}")
    public Tutor getTutorById(@PathVariable UUID id) {
        return this.tutorService.getTutorById(id);
    }

    @PostMapping
    public Tutor novoTutor(@RequestBody TutorDtoRequest tutorDtoRequest) {
       return this.tutorService.setTutorRepository(tutorDtoRequest);
    }



}

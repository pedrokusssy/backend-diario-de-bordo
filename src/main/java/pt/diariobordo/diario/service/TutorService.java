package pt.diariobordo.diario.service;

import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.TutorDtoRequest;
import pt.diariobordo.diario.entity.Tutor;
import pt.diariobordo.diario.repository.TutorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TutorService {
    private TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor setTutorRepository(TutorDtoRequest tutorRepository) {
        Tutor tutor = new Tutor(tutorRepository.nome());
       return this.tutorRepository.save(tutor);
    }

    public List<Tutor> getAllTutor() {
        return this.tutorRepository.findAll();
    }

    public Tutor getTutorById(UUID id) {
        return this.tutorRepository.getOne(id);
    }

}

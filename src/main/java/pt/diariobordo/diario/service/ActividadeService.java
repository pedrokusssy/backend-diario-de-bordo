package pt.diariobordo.diario.service;


import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.ActidadeDtoReponse;
import pt.diariobordo.diario.dto.ActidadeDtoRequest;
import pt.diariobordo.diario.entity.Actividade;
import pt.diariobordo.diario.repository.ActividadeRepositorry;

import java.util.List;
import java.util.UUID;

@Service
public class ActividadeService {

     private final ActividadeRepositorry actividadeRepositor;

    public ActividadeService(ActividadeRepositorry actividadeRepositor) {
        this.actividadeRepositor = actividadeRepositor;
    }

    public Actividade novaActividade(ActidadeDtoRequest actidadeDtoRequest) {
        if (actidadeDtoRequest.actividade().isEmpty()) {
            return null;
        }

        Actividade actividade = new Actividade(actidadeDtoRequest.actividade());
        return this.actividadeRepositor.save(actividade);
    }

    public List<ActidadeDtoReponse> getAllActivadade() {
       return this.actividadeRepositor.findAll().stream().map(ActidadeDtoReponse::new).toList();
    }

    public void deleteActividade (UUID id) {

    }

    public ActidadeDtoReponse updateActividadeById(UUID id) {
        return null;
    }
}

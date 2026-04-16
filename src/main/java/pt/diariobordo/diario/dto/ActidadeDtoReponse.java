package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Actividade;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActidadeDtoReponse(UUID id, String actividade, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public  ActidadeDtoReponse(Actividade actividade){
        this(actividade.getId(), actividade.getActividade(), actividade.getCreatedAt(), actividade.getUpdatedAt());
    }
}

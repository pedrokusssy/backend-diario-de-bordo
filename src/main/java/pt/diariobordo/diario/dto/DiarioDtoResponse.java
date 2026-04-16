package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Actividade;
import pt.diariobordo.diario.entity.Diario;
import pt.diariobordo.diario.entity.Formando;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DiarioDtoResponse(
         UUID id,
         Formando formando,
         Actividade actividade,
         String descricao,
         Boolean assinado,
         LocalDate dataActividade,
         LocalDateTime createdAt,
         LocalDateTime updatedAt
) {
    public DiarioDtoResponse(Diario diario){
        this(diario.getId(), diario.getFormando(), diario.getActividade(), diario.getDescricao(), diario.getAssinado(), diario.getDataActividade(), diario.getCreatedAt(), diario.getUpdatedAt());
    }
}

package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Periodo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record PeriodoDtoResponse(
        UUID id,
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalTime horaInicio,
        LocalTime horaFim,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public PeriodoDtoResponse(Periodo periodo){
        this(periodo.getId(),periodo.getDataInicio(), periodo.getDataFim(), periodo.getHoraInicio(), periodo.getHoraFim(), periodo.getCreatedAt(), periodo.getUpdatedAt());
    }
}

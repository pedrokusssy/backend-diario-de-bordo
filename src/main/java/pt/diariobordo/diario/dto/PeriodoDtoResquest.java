package pt.diariobordo.diario.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record PeriodoDtoResquest(
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalTime horaInicio,
        LocalTime horaFim
) {
}

package pt.diariobordo.diario.dto;


import java.time.LocalDate;

import java.util.UUID;


public record DiarioDtoRequest(
        UUID formando_id,
        UUID actividade_id,
        String descricao,
        LocalDate dataActividade
) {
}

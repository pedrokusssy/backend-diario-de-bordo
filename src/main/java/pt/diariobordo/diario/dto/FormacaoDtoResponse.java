package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.entity.Formando;
import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record FormacaoDtoResponse(

        UUID id,
        String titulo,
        String descricao,
        Periodo periodo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Tutor tutor,
        List<Formando>  formandos


){
    public FormacaoDtoResponse(Formacao formaco){
        this(formaco.getId(), formaco.getTitulo(), formaco.getDescricao(), formaco.getPeriodo(), formaco.getCreatedAt(), formaco.getUpdatedAt(), formaco.getTutor(), formaco.getFormandos());

    }
}

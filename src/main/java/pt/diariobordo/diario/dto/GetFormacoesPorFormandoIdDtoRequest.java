package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Formacao;
import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;

import java.util.UUID;

public record GetFormacoesPorFormandoIdDtoRequest(UUID id,
                                                  String titulo,
                                                  String descricao,
                                                  Tutor tutor,
                                                  Periodo periodo) {

    public GetFormacoesPorFormandoIdDtoRequest(Formacao formacao_) {
        this(formacao_.getId(), formacao_.getTitulo(),formacao_.getDescricao(),
                formacao_.getTutor(),formacao_.getPeriodo());
    }

}

package pt.diariobordo.diario.dto;


import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;

public record FormacaoDtoRequest (


        String titulo,
        String descricao,
        Periodo periodo,
        Tutor tutor,
        String localFormacao


){
}

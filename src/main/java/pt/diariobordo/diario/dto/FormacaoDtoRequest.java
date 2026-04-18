package pt.diariobordo.diario.dto;


import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.entity.Tutor;

import java.util.UUID;


public record FormacaoDtoRequest (


        String titulo,
        String descricao,
        Periodo periodo,
        Tutor tutor


){
}

package pt.diariobordo.diario.dto;

import pt.diariobordo.diario.entity.Formando;

import java.time.LocalDateTime;
import java.util.UUID;

public record FormandoDtoResponde(UUID id, String nome, String hospitalOrigem, LocalDateTime createdAt) {
    public FormandoDtoResponde(Formando formando){
        this(formando.getId(), formando.getNome(), formando.getHospitalOrigem(), formando.getCreatedAt());
    }
}

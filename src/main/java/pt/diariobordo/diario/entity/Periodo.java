package pt.diariobordo.diario.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Table
@Entity
@Getter

@ToString
@EqualsAndHashCode
public class Periodo {
    @Id
    private final UUID id;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private LocalTime horaInicio;
    private LocalTime horaFim;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Periodo(LocalDate dataInicio, LocalDate dataFim, LocalTime horaInicio, LocalTime horaFim) {
        this.id = UUID.randomUUID();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.createdAt = LocalDateTime.now();
    }

    public Periodo(LocalDate dataInicio, LocalDate dataFim) {
        this.id = UUID.randomUUID();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.createdAt = LocalDateTime.now();
    }

    public Periodo() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

}

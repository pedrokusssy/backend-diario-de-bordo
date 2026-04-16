package pt.diariobordo.diario.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Table
@Entity
@Getter

@ToString
@EqualsAndHashCode
public class Actividade {
    @Id
    private final UUID id;

    @Column(unique = true, nullable = false)
    private String actividade;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Actividade(String actividade) {
        this.id = UUID.randomUUID();
        this.actividade = actividade;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Actividade() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}

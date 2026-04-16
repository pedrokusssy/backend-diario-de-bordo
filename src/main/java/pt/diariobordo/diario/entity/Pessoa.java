package pt.diariobordo.diario.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED) // Cria tabelas separadas que se unem por ID
@Getter
@Setter
public abstract class Pessoa {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    private String nif;

    private LocalDateTime createdAt;

    public Pessoa(String nome, String telefone, String nif) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.telefone = telefone;
        this.nif = nif;
        this.createdAt = LocalDateTime.now();
    }

    public Pessoa() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Pessoa(String nome){
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.createdAt = LocalDateTime.now();
    }
}
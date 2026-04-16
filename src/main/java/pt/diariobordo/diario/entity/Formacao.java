package pt.diariobordo.diario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Table
@Entity
@Getter

@ToString
@EqualsAndHashCode
public class Formacao {

    @Id
    private final UUID id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    private String tutor;

    private final LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "formacao_formando", // Nome da tabela que o Hibernate vai criar
            joinColumns = @JoinColumn(name = "formacao_id"),
            inverseJoinColumns = @JoinColumn(name = "formando_id")
    )
    @JsonIgnoreProperties("formacoes") // Evita loop infinito no JSON
    private List<Formando> formandos = new ArrayList<>();


    public Formacao(String titulo, String descricao, Periodo periodo) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDateTime.now();
        this.periodo = periodo;
    }

    public Formacao(String titulo, String descricao, String tutor) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDateTime.now();
    }

    public Formacao(String titulo, String descricao ) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDateTime.now();
    }

    public Formacao(String titulo, Periodo periodo) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.createdAt = LocalDateTime.now();
        this.periodo = periodo;
    }

    public Formacao(String titulo) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.createdAt = LocalDateTime.now();
    }

    public Formacao() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

}
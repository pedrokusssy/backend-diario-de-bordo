package pt.diariobordo.diario.entity;

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
    private UUID id; // REMOVIDO O 'final'

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    @JsonIgnoreProperties("formacoesMinistradas")
    private Tutor tutor;

    @Column(updatable = false)
    private LocalDateTime createdAt; // REMOVIDO O 'final'

    @ManyToMany
    @JoinTable(
            name = "formacao_formando",
            joinColumns = @JoinColumn(name = "formacao_id"),
            inverseJoinColumns = @JoinColumn(name = "formando_id")
    )
    @JsonIgnoreProperties("formacoes")
    private List<Formando> formandos = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    public Formacao(String titulo, String descricao, Periodo periodo) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDateTime.now();
        this.periodo = periodo;
    }

    public Formacao(String titulo, String descricao, Periodo periodo,  Tutor tutor) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDateTime.now();
        this.periodo = periodo;
        this.tutor = tutor;
    }

    public Formacao(String titulo, String descricao, Tutor tutor) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.tutor = tutor;
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

    public Formacao(String titulo, Tutor tutor) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.createdAt = LocalDateTime.now();
        this.tutor = tutor;
    }

    public Formacao() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

}
package pt.diariobordo.diario.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Table
@Entity
@Getter
@Setter

@ToString
@EqualsAndHashCode
public class Diario {
    @Id
    private final UUID id;

    // @OneToMany(mappedBy = "formacao_id", fetch = FetchType.LAZY)
    //private Collection<Formacao> formacoes ;

    @ManyToOne
    @JoinColumn(name = "formando_id")
    private Formando formando;

    @ManyToOne
    @JoinColumn(name = "actividade_id")
    private Actividade actividade;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Boolean assinado;
    private LocalDate dataActividade;
    private final LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Diario(Formando formando, Actividade actividade, String descricao, LocalDate dataActividade) {
        this.id = UUID.randomUUID();
        this.formando = formando;
        this.actividade = actividade;
        this.descricao = descricao;
        this.assinado = false;
        this.dataActividade = dataActividade;
        this.createdAt = LocalDateTime.now();
    }

    public Diario(Formando formando, Actividade actividade, String descricao) {
        this.id = UUID.randomUUID();
        this.formando = formando;
        this.actividade = actividade;
        this.descricao = descricao;
        this.assinado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Diario(Formando formando, Actividade actividade) {
        this.id = UUID.randomUUID();
        this.formando = formando;
        this.actividade = actividade;
        this.assinado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Diario() {
        this.id = UUID.randomUUID();
        this.assinado = false;
        this.createdAt = LocalDateTime.now();
    }

}

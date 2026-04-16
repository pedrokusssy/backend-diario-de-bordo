package pt.diariobordo.diario.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;

@Table
@Entity
@Getter
@Setter

@ToString
@EqualsAndHashCode
public class Tutor extends Pessoa {

    // Herdará id, nome, nif, telefone de Pessoa automaticamente

    private String especialidade;

    // Relacionamentos específicos
    @OneToMany(mappedBy = "tutor")
    private List<Formacao> formacoesMinistradas;

    public Tutor() {
        super();
    }

    public Tutor(String nome, String telefone, String nif, String especialidade, List<Formacao> formacoesMinistradas) {
        super(nome, telefone, nif);
        this.especialidade = especialidade;
        this.formacoesMinistradas = formacoesMinistradas;
    }

    public Tutor(String nome, String especialidade, List<Formacao> formacoesMinistradas) {
        super(nome);
        this.especialidade = especialidade;
        this.formacoesMinistradas = formacoesMinistradas;
    }

    public Tutor(String especialidade, List<Formacao> formacoesMinistradas) {
        this.especialidade = especialidade;
        this.formacoesMinistradas = formacoesMinistradas;
    }
    public Tutor(String nome, String telefone, String nif) {
        super(nome, telefone, nif);
    }
}
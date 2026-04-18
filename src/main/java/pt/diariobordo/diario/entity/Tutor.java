package pt.diariobordo.diario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


@ToString(callSuper = true, exclude = "formacoesMinistradas")
@EqualsAndHashCode(callSuper = true)
public class Tutor extends Pessoa {

    // Herdará id, nome, nif, telefone de Pessoa automaticamente

    // Relacionamentos específicos
    // O mappedBy indica que o mapeamento é controlado pelo campo "tutor" na classe Formacao
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Formacao> formacoesMinistradas = new ArrayList<>();

    public Tutor() {
        super();
    }

    public Tutor(String nome, String telefone, String nif, List<Formacao> formacoesMinistradas) {
        super(nome, telefone, nif);

        this.formacoesMinistradas = formacoesMinistradas;
    }

    public Tutor(String nome, List<Formacao> formacoesMinistradas) {
        super(nome);
        this.formacoesMinistradas = formacoesMinistradas;
    }

    public Tutor(String nome, String telefone, String nif) {
        super(nome, telefone, nif);
    }

    public Tutor(String nome) {
        super(nome);
    }
}
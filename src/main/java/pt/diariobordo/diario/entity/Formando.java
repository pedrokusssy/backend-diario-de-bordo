package pt.diariobordo.diario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@Getter
@Setter
public class Formando extends Pessoa {

    // Herdará id, nome, nif, telefone de Pessoa automaticamente

    private String hospitalOrigem;

    private String especialidade;

    @ManyToMany(mappedBy = "formandos") // Nome do campo na classe Formacao
    @JsonIgnore // Evita que ao carregar o formando, ele tente carregar a formação (loop)
    private List<Formacao> formacoes = new ArrayList<>();

    public Formando() {
        super();
    }

    public Formando(String nome, String telefone, String nif, String hospitalOrigem) {
        super(nome, telefone, nif);
        this.hospitalOrigem = hospitalOrigem;
    }

    public Formando(String nome, String hospitalOrigem) {
        super(nome);
        this.hospitalOrigem = hospitalOrigem;
    }

    public Formando(String hospitalOrigem) {
        this.hospitalOrigem = hospitalOrigem;
    }

}

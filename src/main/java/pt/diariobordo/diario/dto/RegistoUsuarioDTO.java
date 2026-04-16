package pt.diariobordo.diario.dto;

public record RegistoUsuarioDTO(
        // Dados de Credenciais
        String email,
        String password,
        String role, // "FORMANDO" ou "TUTOR"

        // Dados Comuns (Pessoa)
        String nome,
        String telefone,
        String nif,

        // Dados Específicos (Só um deles virá preenchido dependendo da role)
        String hospitalOrigem,
        String especialidade
) {}
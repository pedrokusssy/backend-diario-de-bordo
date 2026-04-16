package pt.diariobordo.diario.dto;

import java.time.LocalDateTime;
import java.util.UUID;

// UsuarioPerfilDTO.java
public record UsuarioPerfilDTO(
        UUID id,
        String nome,
        String email,
        String telefone,
        String nif,
        String hospitalOrigem, // Aqui mapeamos o Hospital ou a Especialidade
        LocalDateTime createdAt,
        String role
) {}

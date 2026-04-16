package pt.diariobordo.diario.dto;

import java.util.UUID;

// No teu pacote de DTOs
public record LoginResponseDTO(
        String token,
        UUID pessoaId,  // O ID que vais usar no React
        String nome,
        String role
) {}
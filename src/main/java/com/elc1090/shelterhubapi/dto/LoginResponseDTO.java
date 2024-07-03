package com.elc1090.shelterhubapi.dto;

public record LoginResponseDTO(
        Long id,
        String cpf,
        String role,
        String token
) {
}

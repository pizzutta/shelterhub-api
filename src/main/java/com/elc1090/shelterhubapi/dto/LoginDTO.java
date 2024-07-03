package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,
        @NotBlank
        String password
) {
}

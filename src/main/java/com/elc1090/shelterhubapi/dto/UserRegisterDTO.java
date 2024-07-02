package com.elc1090.shelterhubapi.dto;

import com.elc1090.shelterhubapi.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(
        Long id,
        @NotBlank String name,
        @NotBlank String cpf,
        String password,
        @NotNull UserRole role
) {
}

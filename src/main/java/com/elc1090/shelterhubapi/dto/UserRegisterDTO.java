package com.elc1090.shelterhubapi.dto;

import com.elc1090.shelterhubapi.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        Long id,
        @NotBlank String name,
        @NotBlank @Size(min = 11, max = 11) String cpf,
        String password,
        @NotNull @Pattern(regexp = "^ADMIN$|^FUNCTIONARY$") UserRole role
) {
}

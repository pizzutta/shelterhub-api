package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VolunteerLoginDTO(
        @NotBlank String name,
        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,
        @NotNull
        Long shelterId
) {
}

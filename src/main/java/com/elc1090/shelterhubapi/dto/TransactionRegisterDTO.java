package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record TransactionRegisterDTO(
        Long id,
        @NotBlank
        @Pattern(regexp = "^INPUT$|^OUTPUT$")
        String action,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Long itemShelterId
) {
}

package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemShelterRegisterDTO(
        @NotNull
        Long itemId,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Long shelterId
) {
}

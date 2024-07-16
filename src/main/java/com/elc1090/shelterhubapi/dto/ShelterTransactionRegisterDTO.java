package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ShelterTransactionRegisterDTO(
        Long id,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Long itemId,
        @NotNull
        Long giverShelterId,
        @NotNull
        Long receiverShelterId
) {
}

package com.elc1090.shelterhubapi.dto;

import com.elc1090.shelterhubapi.model.ActionsEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRegisterDTO(
        Long id,
        @NotNull
        ActionsEnum action,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Long itemShelterId
) {
}

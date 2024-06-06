package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRegisterDTO(
        @NotBlank String name,
        @NotNull Long measurementUnitId,
        @NotNull Long categoryId
) {
}

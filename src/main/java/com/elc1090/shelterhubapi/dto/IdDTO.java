package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotNull;

public record IdDTO(
        @NotNull
        Long id
) {
}

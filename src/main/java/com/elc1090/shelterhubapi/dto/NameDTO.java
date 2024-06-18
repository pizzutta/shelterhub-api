package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;

public record NameDTO(
        Long id,
        @NotBlank String name
) {
}

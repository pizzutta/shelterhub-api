package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShelterRegisterDTO(
        Long id,
        @NotBlank String name,
        @NotNull AddressRegisterDTO address
) {
}

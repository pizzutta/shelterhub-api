package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShelterRegisterDTO(
        @NotBlank String name,
        @NotNull AddressRegisterDTO address
) {
}

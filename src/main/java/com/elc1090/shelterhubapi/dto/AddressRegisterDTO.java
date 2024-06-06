package com.elc1090.shelterhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRegisterDTO(
        @NotBlank String street,
        @NotNull Integer number,
        String complement,
        @NotBlank String district,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode
) {
}

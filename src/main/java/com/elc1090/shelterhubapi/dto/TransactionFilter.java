package com.elc1090.shelterhubapi.dto;

import java.time.LocalDate;

public record TransactionFilter(
        LocalDate initialDate,
        LocalDate finalDate
) {
}

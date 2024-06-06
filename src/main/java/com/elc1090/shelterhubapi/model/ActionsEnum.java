package com.elc1090.shelterhubapi.model;

public enum ActionsEnum {
    INPUT("Entrada"),
    OUTPUT("Saída");

    private final String description;
    ActionsEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

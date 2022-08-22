package br.com.alissonfernandes.cloudparking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusVacancy {

    OCCUPIED("Occupied"),
    AVAILABLE("Available");

    private final String status;
}

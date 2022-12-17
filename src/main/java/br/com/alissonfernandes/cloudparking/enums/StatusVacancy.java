package br.com.alissonfernandes.cloudparking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusVacancy {

    OCCUPIED("Occupied"),
    ONOCCUPIED("Onoccupied");

    private final String status;
}

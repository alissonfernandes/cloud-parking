package br.com.alissonfernandes.cloudparking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleAction {

    ENTRY("Entry"),
    EXIT("Exit");

    private final String action;
}

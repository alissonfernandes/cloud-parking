package br.com.alissonfernandes.cloudparking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleType {

    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    SCOOTER("Scooter"),
    MOPED("Moped"),
    MOTORCICLE_AND_THE_LIKE("Motorcycle_and_the_like"),
    CAR_AND_THE_LIKE("Car_and_the_like");

    private final String type;
}

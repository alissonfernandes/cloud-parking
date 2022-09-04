package br.com.alissonfernandes.cloudparking.exception;

public class VehicleNotFoundException extends Exception {

    public VehicleNotFoundException(Long id) {
        super("Vehicle not found with ID: " + id);
    }
}

package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
import br.com.alissonfernandes.cloudparking.model.Parking;
import br.com.alissonfernandes.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/entry")
    public Parking entry(@RequestBody VehicleDTO vehicleDTO) {
       return parkingService.entry(vehicleDTO);
    }

    @GetMapping("/exit/{id}")
    public Parking exit(@PathVariable Long id) throws VacancyNotFoundException {
        return parkingService.exit(id);
    }
}

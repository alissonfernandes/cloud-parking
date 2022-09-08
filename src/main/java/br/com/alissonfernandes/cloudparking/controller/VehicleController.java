package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.exception.VehicleNotFoundException;
import br.com.alissonfernandes.cloudparking.service.impl.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDTO createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.entry(vehicleDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public VehicleDTO get(@PathVariable Long id) throws VehicleNotFoundException {
        return vehicleService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDTO> getListAll() {
        return vehicleService.listAll();
    }

    @PutMapping("/{id}/exit")
    public VehicleDTO exitvehicle(@PathVariable Long id) throws VehicleNotFoundException {
        return vehicleService.exit(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public VehicleDTO updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        return vehicleService.update(id, vehicleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id) throws VehicleNotFoundException {
        vehicleService.delete(id);
    }
}

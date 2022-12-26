package br.com.alissonfernandes.cloudparking.service.impl;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.exception.VehicleNotFoundException;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import br.com.alissonfernandes.cloudparking.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleService {

    private VehicleRepository vehicleRepository;

    private VehicleMapper vehicleMapper;

    public VehicleDTO save(VehicleDTO vehicleDTO) {
        return this.vehicleSave(vehicleDTO);
    }

    public VehicleDTO get(Long id) throws VehicleNotFoundException {
       VehicleDTO vehicleReturned = this.verifyIfExists(id);
       return vehicleReturned;
    }

    public List<VehicleDTO> listAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(v -> vehicleMapper.toDTO(v)).collect(Collectors.toList());
    }

    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        this.verifyIfExists(id);
        return this.vehicleSave(vehicleDTO);
    }

    public VehicleDTO delete(Long id) throws VehicleNotFoundException {
        VehicleDTO vehicle = this.verifyIfExists(id);
        return this.vehicleSave(vehicle);
    }

    private VehicleDTO verifyIfExists(Long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        return vehicleMapper.toDTO(vehicle);
    }

    private VehicleDTO vehicleSave(VehicleDTO vehicleDTO) {
        Vehicle vehicletoSalve = vehicleMapper.toModel(vehicleDTO);
        Vehicle vehicleSaved = vehicleRepository.save(vehicletoSalve);

        return vehicleMapper.toDTO(vehicleSaved);
    }
}

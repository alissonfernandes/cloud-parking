package br.com.alissonfernandes.cloudparking.service.impl;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.exception.VehicleNotFoundException;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import br.com.alissonfernandes.cloudparking.repository.VehicleRepository;
import br.com.alissonfernandes.cloudparking.service.IVehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleServiceImpl implements IVehicleService {

    private VehicleRepository vehicleRepository;

    private VehicleMapper vehicleMapper;

    @Override
    public VehicleDTO create(VehicleDTO vehicleDTO) {
        Vehicle vehicleToSave = vehicleMapper.toModel(vehicleDTO);
        Vehicle vehicleSaved = vehicleRepository.save(vehicleToSave);
        return vehicleMapper.toDTO(vehicleSaved);
    }

    @Override
    public VehicleDTO get(Long id) throws VehicleNotFoundException {
       Vehicle vehicleReturned = this.verifyIfExists(id);
       return vehicleMapper.toDTO(vehicleReturned);
    }

    @Override
    public List<VehicleDTO> listAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(v -> vehicleMapper.toDTO(v)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        Vehicle vehiclereturned = this.verifyIfExists(id);
        return vehicleMapper.toDTO(vehiclereturned);
    }

    @Override
    public void delete(Long id) throws VehicleNotFoundException {
        this.verifyIfExists(id);
        vehicleRepository.deleteById(id);
    }

    private Vehicle verifyIfExists(Long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        return vehicle;
    }
}

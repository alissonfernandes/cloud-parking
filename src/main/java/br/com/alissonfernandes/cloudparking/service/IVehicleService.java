package br.com.alissonfernandes.cloudparking.service;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.exception.VehicleNotFoundException;

import java.util.List;

public interface IVehicleService {

    VehicleDTO create(VehicleDTO vehicleDTO);
    VehicleDTO get(Long id) throws VehicleNotFoundException;
    List<VehicleDTO> listAll();
    VehicleDTO update(Long id, VehicleDTO vehicleDTO) throws VehicleNotFoundException;
    void delete(Long id) throws VehicleNotFoundException;
}

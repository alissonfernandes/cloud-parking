package br.com.alissonfernandes.cloudparking.service;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;

import java.util.List;

public interface IVehicleService {

    VehicleDTO create(VehicleDTO vehicleDTO);
    VehicleDTO get(Long id);
    List<VehicleDTO> listAll();
    VehicleDTO update(Long id, VehicleDTO vehicleDTO);
    void delete(Long id);
}

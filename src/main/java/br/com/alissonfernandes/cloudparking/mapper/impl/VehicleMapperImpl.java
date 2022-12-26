package br.com.alissonfernandes.cloudparking.mapper.impl;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Vehicle;

public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toModel(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) return null;

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleDTO.getId() );
        vehicle.setVehicleType( vehicleDTO.getVehicleType() );
        vehicle.setPlaca( vehicleDTO.getPlaca() );
        vehicle.setColor( vehicleDTO.getColor() );
        vehicle.setDriveName( vehicleDTO.getDriveName() );

        return vehicle;
    }

    @Override
    public VehicleDTO toDTO(Vehicle vehicle) {
        if (vehicle == null) return null;

        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        vehicleDTO.setPlaca(vehicle.getPlaca());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setDriveName(vehicle.getDriveName());

        return vehicleDTO;
    }
}

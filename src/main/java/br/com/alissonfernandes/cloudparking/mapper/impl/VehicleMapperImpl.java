package br.com.alissonfernandes.cloudparking.mapper.impl;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.mapper.VacancyMapper;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import org.mapstruct.factory.Mappers;

public class VehicleMapperImpl implements VehicleMapper {

    private final VacancyMapper vacancyMapper = Mappers.getMapper(VacancyMapper.class);

    @Override
    public Vehicle toModel(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) return null;

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleDTO.getId() );
        vehicle.setVehicleType( vehicleDTO.getVehicleType() );
        vehicle.setPlaca( vehicleDTO.getPlaca() );
        vehicle.setColor( vehicleDTO.getColor() );
        vehicle.setEntryDate( vehicleDTO.getEntryDate() );
        vehicle.setExitDate( vehicleDTO.getExitDate() );
        vehicle.setBill( vehicleDTO.getBill() );
        vehicle.setDriveName( vehicleDTO.getDriveName() );
        vehicle.setVacancy(vacancyMapper.toModel(vehicleDTO.getVacancyDTO()));

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
        vehicleDTO.setEntryDate(vehicle.getEntryDate());
        vehicleDTO.setExitDate(vehicle.getExitDate());
        vehicleDTO.setBill(vehicle.getBill());
        vehicleDTO.setDriveName(vehicle.getDriveName());
        vehicleDTO.setVacancyDTO(vacancyMapper.toDTO(vehicle.getVacancy()));

        return vehicleDTO;
    }
}

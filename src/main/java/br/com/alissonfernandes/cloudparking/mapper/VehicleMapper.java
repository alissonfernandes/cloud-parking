package br.com.alissonfernandes.cloudparking.mapper;

import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle toModel(VehicleDTO vehicleDTO);
    VehicleDTO toDTO(Vehicle vehicle);
}

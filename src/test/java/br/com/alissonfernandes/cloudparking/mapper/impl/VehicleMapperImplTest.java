package br.com.alissonfernandes.cloudparking.mapper.impl;

import br.com.alissonfernandes.cloudparking.builder.VehicleDTOBuilder;
import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("testando implementação do VahicleMapper")
public class VehicleMapperImplTest {

    VehicleMapper vehicleMapper = new VehicleMapperImpl();

    @Test
    @DisplayName("retorne VehicleModel quando o método toModel for chamado")
    public void whenToModelIsCalledThenVehicleModelIsReturned() {
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle vehicleModel = vehicleMapper.toModel(vehicleDTO);

        assertEquals(vehicleDTO.getId(), vehicleModel.getId());
        assertEquals(vehicleDTO.getVehicleType(), vehicleModel.getVehicleType());
        assertEquals(vehicleDTO.getPlaca(), vehicleModel.getPlaca());
        assertEquals(vehicleDTO.getColor(), vehicleModel.getColor());
        assertEquals(vehicleDTO.getBill(), vehicleModel.getBill());
        assertEquals(vehicleDTO.getDriveName(), vehicleModel.getDriveName());
        assertEquals(vehicleDTO.getEntryDate(), vehicleModel.getEntryDate());
        assertEquals(vehicleDTO.getEntryDate(), vehicleModel.getExitDate());
    }

    @Test
    @DisplayName("retorne VehicleDTO quando o método toDTO for chamado")
    public void whenTODTOIsCalledThenVehicleDTOIsReturned() {
        VehicleDTO vehicleDTOToMapper = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle vehicleModel = vehicleMapper.toModel(vehicleDTOToMapper);

        VehicleDTO vehicleDTO = vehicleMapper.toDTO(vehicleModel);

        assertEquals(vehicleModel.getId(), vehicleDTO.getId());
        assertEquals(vehicleModel.getPlaca(), vehicleDTO.getPlaca());
        assertEquals(vehicleModel.getColor(), vehicleDTO.getColor());
        assertEquals(vehicleModel.getVehicleType(), vehicleDTO.getVehicleType());
        assertEquals(vehicleModel.getBill(), vehicleDTO.getBill());
        assertEquals(vehicleModel.getDriveName(), vehicleDTO.getDriveName());
        assertEquals(vehicleModel.getEntryDate(), vehicleDTO.getEntryDate());
        assertEquals(vehicleModel.getExitDate(), vehicleDTO.getExitDate());
    }
}

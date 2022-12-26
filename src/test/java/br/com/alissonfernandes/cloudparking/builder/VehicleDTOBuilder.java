package br.com.alissonfernandes.cloudparking.builder;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class VehicleDTOBuilder {

    @Builder.Default
    private long id = 1L;

    @Builder.Default
    private VehicleType vehicleType = VehicleType.CAR;

    @Builder.Default
    private String placa = "AAA-123";

    @Builder.Default
    private String color = "black";

    @Builder.Default
    private LocalDateTime entryDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime exitDate = LocalDateTime.now();

    @Builder.Default
    private Double bill = 10.00D;

    @Builder.Default
    private String driveName = "Drive Name";

    @Builder.Default
    private VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

    public VehicleDTO toVehicleDTO() {
        return new VehicleDTO(id, vehicleType, placa, color, driveName);
    }
}

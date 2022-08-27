package br.com.alissonfernandes.cloudparking.builder;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.enums.StatusVacancy;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.Builder;

@Builder
public class VacancyDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private StatusVacancy status = StatusVacancy.AVAILABLE;

    @Builder.Default
    private VehicleType vehicleType = VehicleType.CAR;

    private VehicleDTO vehicle;

    public VacancyDTO toVacancyDTO() {
        return new VacancyDTO(id, status, vehicleType, vehicle);
    }
}

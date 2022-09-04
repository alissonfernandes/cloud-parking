package br.com.alissonfernandes.cloudparking.dto;

import br.com.alissonfernandes.cloudparking.enums.StatusVacancy;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDTO {

    private Long id;
    private Long number;
    private StatusVacancy status;
    private VehicleType vehicleType;
    private VehicleDTO vehicle;

}

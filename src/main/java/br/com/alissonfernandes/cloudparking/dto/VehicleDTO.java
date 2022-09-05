package br.com.alissonfernandes.cloudparking.dto;

import br.com.alissonfernandes.cloudparking.enums.VehicleAction;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private long id;
    private VehicleType vehicleType;
    private String placa;
    private String color;
    private VehicleAction vehicleAction;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
    private String driveName;
    private VacancyDTO vacancyDTO;
}

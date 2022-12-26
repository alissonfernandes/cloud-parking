package br.com.alissonfernandes.cloudparking.dto;

import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private long id;
    private VehicleType vehicleType;
    private String placa;
    private String color;
    private String driveName;
}

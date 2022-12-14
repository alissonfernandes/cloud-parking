package br.com.alissonfernandes.cloudparking.model;

import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type")
    private VehicleType vehicleType;

    @Column(nullable = false, name = "vehicle_id")
    private String placa;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String driveName;

}

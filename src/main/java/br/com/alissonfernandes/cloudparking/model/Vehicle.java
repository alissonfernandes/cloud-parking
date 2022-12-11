package br.com.alissonfernandes.cloudparking.model;

import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

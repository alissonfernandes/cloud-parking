package br.com.alissonfernandes.cloudparking.model;

import br.com.alissonfernandes.cloudparking.enums.StatusVacancy;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVacancy status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;
}

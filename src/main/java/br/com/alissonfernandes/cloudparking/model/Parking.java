package br.com.alissonfernandes.cloudparking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_parking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column(nullable = true)
    private LocalDateTime exitDate;

    @Column(nullable = true)
    private Double bill;

    @OneToOne
    private Vacancy vacancy;

    @OneToOne
    private Vehicle vehicle;
}

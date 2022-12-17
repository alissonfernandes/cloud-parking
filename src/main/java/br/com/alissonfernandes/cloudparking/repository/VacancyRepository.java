package br.com.alissonfernandes.cloudparking.repository;

import br.com.alissonfernandes.cloudparking.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(value = "SELECT V FROM Vacancy as V WHERE V.status='UNOCCUPIED'")
    List<Vacancy> findAllVacancyOnoccupied();

    @Query(value = "SELECT V FROM Vacancy as V WHERE V.status='UNOCCUPIED' AND V.vehicleType='CAR'")
    List<Vacancy> findAllVacancyCarOnoccupied();

    @Query(value = "SELECT V FROM Vacancy as V WHERE V.status='UNOCCUPIED' AND V.vehicleType='MOTORCYCLE'")
    List<Vacancy> findAllVacancyMotorcycleOnoccupied();
}

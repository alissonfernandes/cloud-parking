package br.com.alissonfernandes.cloudparking.repository;

import br.com.alissonfernandes.cloudparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VahicleRepository extends JpaRepository<Vehicle, Long> {
}

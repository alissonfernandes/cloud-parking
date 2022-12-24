package br.com.alissonfernandes.cloudparking.service;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.dto.VehicleDTO;
import br.com.alissonfernandes.cloudparking.enums.StatusVacancy;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
import br.com.alissonfernandes.cloudparking.mapper.VehicleMapper;
import br.com.alissonfernandes.cloudparking.model.Parking;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import br.com.alissonfernandes.cloudparking.model.Vehicle;
import br.com.alissonfernandes.cloudparking.repository.ParkingRepository;
import br.com.alissonfernandes.cloudparking.repository.VacancyRepository;
import br.com.alissonfernandes.cloudparking.repository.VehicleRepository;
import br.com.alissonfernandes.cloudparking.service.impl.VacancyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private VacancyServiceImpl vacancyService;

    @Autowired
    private VehicleMapper vehicleMapper;

    public Parking entry(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toModel(vehicleDTO);

        Vacancy vacancy = null;

        if (vehicleDTO.getVehicleType() == VehicleType.CAR) {
           List<Vacancy> vacancies = vacancyRepository.findAllVacancyCarUnoccupied();
           if (vacancies.isEmpty()) System.out.println("no vacancies to car");
           else vacancy = vacancies.get(0);
        } else if (vehicleDTO.getVehicleType() == VehicleType.MOTORCYCLE) {
            List<Vacancy> vacancies = vacancyRepository.findAllVacancyMotorcycleUnoccupied();
            if (vacancies.isEmpty()) System.out.println("no vacancies to motorcycle");
            else vacancy = vacancies.get(0);
        }

        vacancy.setStatus(StatusVacancy.OCCUPIED);

        Parking parking = Parking.builder().vehicle(vehicleRepository.save(vehicle))
                        .entryDate(LocalDateTime.now())
                .vacancy(vacancy).build();
       return parkingRepository.save(parking);
    }

    public Parking exit(Long id) throws VacancyNotFoundException {
        Parking parking = this.verifyIfExists(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(this.calcBill(parking));

        VacancyDTO vacancyDTO = vacancyService.get(parking.getVacancy().getId());
        vacancyDTO.setStatus(StatusVacancy.UNOCCUPIED);
        vacancyService.update(parking.getVacancy().getId(), vacancyDTO);

        return parkingRepository.save(parking);
    }

    private Parking verifyIfExists(Long id) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow();
        return  parking;
    }

    private Double calcBill(Parking parking) {
        // this method still needs to be implemented
        return 20.50d;
    }
}

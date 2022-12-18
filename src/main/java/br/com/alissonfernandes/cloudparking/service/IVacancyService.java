package br.com.alissonfernandes.cloudparking.service;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.enums.StatusVacancy;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;

import java.util.List;

public interface IVacancyService {

    VacancyDTO create(VacancyDTO vacancyDTO);
    VacancyDTO get(Long id) throws VacancyNotFoundException;
    List<VacancyDTO> listAll();

    List<VacancyDTO> listAllVacancyUnoccupied(VehicleType vehicleType);

    VacancyDTO update(Long id, VacancyDTO vacancyDTO) throws VacancyNotFoundException;
    void delete(Long id) throws VacancyNotFoundException;
}

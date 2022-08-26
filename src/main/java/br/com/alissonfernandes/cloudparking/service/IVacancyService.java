package br.com.alissonfernandes.cloudparking.service;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;

import java.util.List;

public interface IVacancyService {

    VacancyDTO create(VacancyDTO vacancyDTO);
    VacancyDTO get(Long id);
    List<VacancyDTO> listAll();
    VacancyDTO update(Long id, VacancyDTO vacancyDTO);
    void delete(Long id);
}

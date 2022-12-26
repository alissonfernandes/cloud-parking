package br.com.alissonfernandes.cloudparking.mapper.impl;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.mapper.VacancyMapper;
import br.com.alissonfernandes.cloudparking.model.Vacancy;

public class VacancyMapperImpl implements VacancyMapper {

    @Override
    public Vacancy toModel(VacancyDTO vacancyDTO) {
        if (vacancyDTO == null) return null;

        Vacancy vacancy = new Vacancy();
        vacancy.setId(vacancyDTO.getId());
        vacancy.setNumber(vacancyDTO.getNumber());
        vacancy.setStatus(vacancyDTO.getStatus());
        vacancy.setVehicleType(vacancyDTO.getVehicleType());
        return vacancy;
    }

    @Override
    public VacancyDTO toDTO(Vacancy vacancy) {
        if (vacancy == null) return null;

        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setId(vacancy.getId());
        vacancyDTO.setNumber(vacancy.getNumber());
        vacancyDTO.setStatus(vacancy.getStatus());
        vacancyDTO.setVehicleType(vacancy.getVehicleType());
        return vacancyDTO;
    }
}

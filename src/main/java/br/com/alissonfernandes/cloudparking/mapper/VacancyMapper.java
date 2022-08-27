package br.com.alissonfernandes.cloudparking.mapper;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VacancyMapper {

    VacancyMapper INSTANCE = Mappers.getMapper(VacancyMapper.class);

    Vacancy toModel(VacancyDTO vacancyDTO);
    VacancyDTO toDTO(Vacancy vacancy);
}

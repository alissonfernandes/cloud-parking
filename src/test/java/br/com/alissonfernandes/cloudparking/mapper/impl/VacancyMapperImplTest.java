package br.com.alissonfernandes.cloudparking.mapper.impl;

import br.com.alissonfernandes.cloudparking.builder.VacancyDTOBuilder;
import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.mapper.VacancyMapper;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testando implementação do VacancyMapper")
public class VacancyMapperImplTest {


    VacancyMapper vacancyMapper = new VacancyMapperImpl();

    @Test
    @DisplayName("retorne VacancyModel quando o método toModel for chamado")
    public void whenToModelIsCalledThenVacancyModelIsReturned() {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTO);

        assertEquals(vacancyDTO.getId(), vacancyModel.getId());
        assertEquals(vacancyDTO.getStatus(), vacancyModel.getStatus());
        assertEquals(vacancyDTO.getVehicleType(), vacancyModel.getVehicleType());
    }

    @Test
    @DisplayName("retorne VacancyDTO quando o método toDTO for chamado")
    public void whenToDTOIsCalledThenVacancyDTOIsReturned() {
        VacancyDTO vacancyDTOToModel = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTOToModel);

        VacancyDTO vacancyDTO = vacancyMapper.toDTO(vacancyModel);

        assertEquals(vacancyModel.getId(), vacancyDTO.getId());
        assertEquals(vacancyModel.getVehicleType(), vacancyDTO.getVehicleType());
        assertEquals(vacancyModel.getStatus(), vacancyDTO.getStatus());
    }
}

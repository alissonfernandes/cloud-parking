package br.com.alissonfernandes.cloudparking.mapper;

import br.com.alissonfernandes.cloudparking.builder.VacancyDTOBuilder;
import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import org.mapstruct.factory.Mappers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacancyMapperTest {

    private VacancyMapper vacancyMapper = Mappers.getMapper(VacancyMapper.class);

    @Test
    public void whenToModelIsCalledThenVacancyModelIsReturned() {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTO);

        assertEquals(vacancyDTO.getId(), vacancyModel.getId());
        assertEquals(vacancyDTO.getStatus(), vacancyModel.getStatus());
        assertEquals(vacancyDTO.getVehicleType(), vacancyModel.getVehicleType());
    }

    @Test
    public void whenTODTOIsCallesThenVacancyDTOIsReturned() {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTO);

        assertEquals(vacancyModel.getId(), vacancyDTO.getId());
        assertEquals(vacancyModel.getStatus(), vacancyDTO.getStatus());
        assertEquals(vacancyModel.getVehicleType(), vacancyDTO.getVehicleType());
    }
}

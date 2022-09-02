package br.com.alissonfernandes.cloudparking.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.alissonfernandes.cloudparking.builder.VacancyDTOBuilder;
import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
import br.com.alissonfernandes.cloudparking.mapper.VacancyMapper;
import br.com.alissonfernandes.cloudparking.mapper.impl.VacancyMapperImpl;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import br.com.alissonfernandes.cloudparking.repository.VacancyRepository;
import br.com.alissonfernandes.cloudparking.service.IVacancyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("testando implementação do VacancyService")
public class VacancyServiceImplTest {

    @Mock
    private VacancyRepository vacancyRepository;

    @InjectMocks
    private IVacancyService vacancyService = new VacancyServiceImpl();

    VacancyMapper vacancyMapper = new VacancyMapperImpl();

    @Test
    @DisplayName("quando uma vacancy for informada então um vacancy deve ser criada")
    void whenVacancyInformedThenItShouldBeCreated() {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTO);

        when(vacancyRepository.save(vacancyModel)).thenReturn(vacancyModel);

        VacancyDTO vacancyDTOCreated = vacancyService.create(vacancyDTO);
        assertThat(vacancyDTOCreated.getId(), is(equalTo(vacancyDTO.getId())));
        assertThat(vacancyDTOCreated.getStatus(), is(equalTo(vacancyDTO.getStatus())));
        assertThat(vacancyDTOCreated.getVehicleType(), is(equalTo(vacancyDTO.getVehicleType())));
    }

    @Test
    @DisplayName("quando o id valido de um vacancy for informado então retorne um vacancy")
    void whenValidVacancyIdIsGivenThenReturnAVacancy() throws VacancyNotFoundException {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancyModel = vacancyMapper.toModel(vacancyDTO);

        when(vacancyRepository.findById(vacancyDTO.getId())).thenReturn(Optional.of(vacancyModel));

        VacancyDTO vacancyDTOFound = vacancyService.get(vacancyDTO.getId());
        assertThat(vacancyDTOFound, is(equalTo(vacancyDTO)));
    }

    @Test
    @DisplayName("quando lista vacancy for chamado então retorne uma lista de vacancys")
    void whenListVacancyIsCalledThenReturnAListOfVacancys() {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();
        Vacancy vacancy = vacancyMapper.toModel(vacancyDTO);

        when(vacancyRepository.findAll()).thenReturn(Collections.singletonList(vacancy));

        List<VacancyDTO> vacancys = vacancyService.listAll();
        assertThat(vacancys, is(not(empty())));
        assertThat(vacancys.get(0), is(equalTo(vacancyDTO)));
    }

    @Test
    @DisplayName("quando lista de vacancy é chamado então retorne uma lista de vacancys vazio")
    void whenListVacancyIsCalledThenReturnAnEmptyListOfVacancys() {
        when(vacancyRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<VacancyDTO> vacancys = vacancyService.listAll();
        assertThat(vacancys, is(empty()));
    }

}

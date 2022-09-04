package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.builder.VacancyDTOBuilder;
import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
import br.com.alissonfernandes.cloudparking.service.IVacancyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("testando métodos HTTP do Vacancy Controller")
public class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    IVacancyService vacancyService;

    @InjectMocks
    VacancyController vacancyController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacancyController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    private static final String VACANCY_API_URL_PATH = "/api/v1/vacancy";
    private static final long VALID_VACANCY_ID = 1L;
    private static final long INVALID_VACANCY_ID = 2L;

    @Test
    @DisplayName("quando POST é chamado então um vacancy é criado")
    void whenPOSTIsCalledThenAVacancyIsCreated() throws Exception {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        when(vacancyService.create(vacancyDTO)).thenReturn(vacancyDTO);

        mockMvc.perform(post(VACANCY_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacancyDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(not(empty()))))
                .andExpect(jsonPath("$.status", is(vacancyDTO.getStatus().getStatus().toUpperCase())))
                .andExpect(jsonPath("$.vehicleType", is(vacancyDTO.getVehicleType().getType().toUpperCase())));
    }

    @Test
    @DisplayName("quando GET é chamado então um vacancy é retornado")
    void whenGETIsCalledThenAVacancyIsReturned() throws Exception {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        when(vacancyService.get(vacancyDTO.getId())).thenReturn(vacancyDTO);

        mockMvc.perform(get(VACANCY_API_URL_PATH + "/" + VALID_VACANCY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacancyDTO)))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id", is(not(empty()))))
                .andExpect(jsonPath("$.status", is(vacancyDTO.getStatus().getStatus().toUpperCase())))
                .andExpect(jsonPath("$.vehicleType", is(vacancyDTO.getVehicleType().getType().toUpperCase())));
    }

    @Test
    @DisplayName("quando HTTP GET (com parâmetro inválido) for chamado, então NotFound status é retornado")
    void whenGETIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        when(vacancyService.get(vacancyDTO.getId())).thenThrow(VacancyNotFoundException.class);

        mockMvc.perform(get(VACANCY_API_URL_PATH + "/" + vacancyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("quando HTTP GET (sem parâmetro) for chamado então retorne uma lista de vacancys")
    void whenGETListWithVacanysIsCalledThenReturnAListAllVacancys() throws Exception {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        when(vacancyService.listAll()).thenReturn(Collections.singletonList(vacancyDTO));

        mockMvc.perform(get(VACANCY_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(not(empty()))))
                .andExpect(jsonPath("$[0].status", is(vacancyDTO.getStatus().getStatus().toUpperCase())))
                .andExpect(jsonPath("$[0].vehicleType", is(vacancyDTO.getVehicleType().getType().toUpperCase())));
    }

    @Test
    @DisplayName("quando o método HTTP PUT é chamado então um vacancy é atualizado e retornado")
    void whenPUTIsCalledThenAVacancyIsUpdated() throws Exception {
        VacancyDTO vacancyDTO = VacancyDTOBuilder.builder().build().toVacancyDTO();

        when(vacancyService.update(vacancyDTO.getId(), vacancyDTO)).thenReturn(vacancyDTO);

        mockMvc.perform(put(VACANCY_API_URL_PATH + "/" + VALID_VACANCY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacancyDTO)))
                .andExpect(status().isUpgradeRequired())
                .andExpect(jsonPath("$.id", is(not(empty()))))
                .andExpect(jsonPath("$.status", is(vacancyDTO.getStatus().getStatus().toUpperCase())))
                .andExpect(jsonPath("$.vehicleType", is(vacancyDTO.getVehicleType().getType().toUpperCase())));
    }

    @Test
    @DisplayName("quando HTTP DELETE for chamado com um id válido então status NoContent é retornado")
    void whenDELETEIsCalledWithValidIdThenANoContentStatusIsReturned() throws Exception {
        doNothing().when(vacancyService).delete(VALID_VACANCY_ID);

        mockMvc.perform(delete(VACANCY_API_URL_PATH + "/" + VALID_VACANCY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("quando HTTP DELETE for chamado passando um ID Inválido, então status NotFound é retornado")
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        doThrow(VacancyNotFoundException.class).when(vacancyService).delete(INVALID_VACANCY_ID);

        mockMvc.perform(delete(VACANCY_API_URL_PATH + "/" + INVALID_VACANCY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}

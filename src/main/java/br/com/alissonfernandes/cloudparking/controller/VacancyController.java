package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyDTO createVacancy(VacancyDTO vacancyDTO) {
        return vacancyService.create(vacancyDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public VacancyDTO getVacancy(@PathVariable Long id, VacancyDTO vacancyDTO) {
        return vacancyService.get(id, vacancyDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDTO> getListAll() {
        return vacancyService.listAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public VacancyDTO updateVacancy(@PathVariable Long id, @RequestBody VacancyDTO vacancyDTO) {
        return vacancyService.update(id, vacancyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVacancy(Long id) {
        vacancyService.delete(id);
    }
}

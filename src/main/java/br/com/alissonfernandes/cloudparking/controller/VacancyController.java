package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.service.IVacancyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VacancyController {

    private IVacancyService vacancyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyDTO createVacancy(@RequestBody VacancyDTO vacancyDTO) {
        return vacancyService.create(vacancyDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public VacancyDTO getVacancy(@PathVariable Long id) {
        return vacancyService.get(id);
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
    public void deleteVacancy(@PathVariable Long id) {
        vacancyService.delete(id);
    }
}

package br.com.alissonfernandes.cloudparking.controller;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
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
    public VacancyDTO getVacancy(@PathVariable Long id) throws VacancyNotFoundException {
        return vacancyService.get(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDTO> getListAll() {
        return vacancyService.listAll();
    }

    @GetMapping("/all/unoccupied")
    @ResponseStatus(HttpStatus.OK)
    public  List<VacancyDTO> getAllVacancyCarOnoccpied() {
        return vacancyService.listAllVacancyUnoccupied(null);
    }

    @GetMapping("/all/unoccupied/car")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDTO> getAllVacancyCarOnoccupied() {
        return vacancyService.listAllVacancyUnoccupied(VehicleType.CAR);
    }

    @GetMapping("/all/unoccupied/motorcycle")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyDTO> getAllVacancyMotorcycleOnoccupied() {
        return vacancyService.listAllVacancyUnoccupied(VehicleType.MOTORCYCLE);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public VacancyDTO updateVacancy(@PathVariable Long id, @RequestBody VacancyDTO vacancyDTO) throws VacancyNotFoundException {
        return vacancyService.update(id, vacancyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVacancy(@PathVariable Long id) throws VacancyNotFoundException {
        vacancyService.delete(id);
    }
}

package br.com.alissonfernandes.cloudparking.service.impl;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
import br.com.alissonfernandes.cloudparking.enums.VehicleType;
import br.com.alissonfernandes.cloudparking.exception.VacancyNotFoundException;
import br.com.alissonfernandes.cloudparking.mapper.VacancyMapper;
import br.com.alissonfernandes.cloudparking.mapper.impl.VacancyMapperImpl;
import br.com.alissonfernandes.cloudparking.model.Vacancy;
import br.com.alissonfernandes.cloudparking.repository.VacancyRepository;
import br.com.alissonfernandes.cloudparking.service.IVacancyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VacancyServiceImpl implements IVacancyService {


    private VacancyRepository vacancyRepository;

    private VacancyMapper vacancyMapper;

    public VacancyServiceImpl() {
        vacancyMapper = new VacancyMapperImpl();
    }

    @Override
    public VacancyDTO create(VacancyDTO vacancyDTO) {
        Vacancy vacancyToSave = vacancyMapper.toModel(vacancyDTO);
        Vacancy vacancySaved = vacancyRepository.save(vacancyToSave);
        return vacancyMapper.toDTO(vacancySaved);
    }

    @Override
    public VacancyDTO get(Long id) throws VacancyNotFoundException {
        Vacancy vacancy = this.verifyIfExists(id);
        return vacancyMapper.toDTO(vacancy);
    }

    @Override
    public List<VacancyDTO> listAll() {
        List<Vacancy> vacancyList = vacancyRepository.findAll();
        return vacancyList.stream()
                .map(v -> vacancyMapper.toDTO(v))
                .collect(Collectors.toList());
    }

    @Override
    public List<VacancyDTO> listAllVacancyOnoccupied(VehicleType vehicleType){
        List<Vacancy> vacancyList = null;
        if (vehicleType == null) vacancyList = vacancyRepository.findAllVacancyOnoccupied();
        else if (vehicleType == VehicleType.CAR) vacancyList = vacancyRepository.findAllVacancyCarOnoccupied();
        else if (vehicleType == VehicleType.MOTORCYCLE) vacancyList = vacancyRepository.findAllVacancyMotorcycleOnoccupied();
        return vacancyList.stream().map(v -> vacancyMapper.toDTO(v)).collect(Collectors.toList());
    }

    @Override
    public VacancyDTO update(Long id, VacancyDTO vacancyDTO) throws VacancyNotFoundException {
        this.verifyIfExists(id);
        Vacancy vacancyToSave = vacancyMapper.toModel(vacancyDTO);
        Vacancy vacancyReturned = vacancyRepository.save(vacancyToSave);
        return vacancyMapper.toDTO(vacancyReturned);
    }

    @Override
    public void delete(Long id) throws VacancyNotFoundException {
        this.verifyIfExists(id);
        vacancyRepository.deleteById(id);
    }

    private Vacancy verifyIfExists(Long id) throws VacancyNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException(id));
        return vacancy;
    }

}

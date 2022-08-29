package br.com.alissonfernandes.cloudparking.service.impl;

import br.com.alissonfernandes.cloudparking.dto.VacancyDTO;
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
    public VacancyDTO get(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id).get();
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
    public VacancyDTO update(Long id, VacancyDTO vacancyDTO) {
        Vacancy vacancyToSave = vacancyMapper.toModel(vacancyDTO);
        Vacancy vacancyReturned = vacancyRepository.save(vacancyToSave);
        return vacancyMapper.toDTO(vacancyReturned);
    }

    @Override
    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }
}

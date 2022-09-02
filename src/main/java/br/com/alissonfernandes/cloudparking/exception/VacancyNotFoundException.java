package br.com.alissonfernandes.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VacancyNotFoundException extends Exception {

    public VacancyNotFoundException(Long id) {
        super("Vacancy not found with ID " + id);
    }
}

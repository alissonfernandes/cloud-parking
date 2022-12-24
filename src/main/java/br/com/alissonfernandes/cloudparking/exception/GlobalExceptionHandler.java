package br.com.alissonfernandes.cloudparking.exception;

import br.com.alissonfernandes.cloudparking.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VacancyNotFoundException.class)
    public ResponseEntity<ExceptionDTO> NotFoundException(VacancyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(NoVacanciesException.class)
    public ResponseEntity<ExceptionDTO> NoVacanciesException(NoVacanciesException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(VacancyTypeException.class)
    public ResponseEntity<ExceptionDTO> VacancyTypeException(VacancyTypeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}

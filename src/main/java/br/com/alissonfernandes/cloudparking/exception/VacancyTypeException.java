package br.com.alissonfernandes.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VacancyTypeException extends Exception{

    public VacancyTypeException(String str) {
        super("VacancyType not exists: " + str);
    }
}

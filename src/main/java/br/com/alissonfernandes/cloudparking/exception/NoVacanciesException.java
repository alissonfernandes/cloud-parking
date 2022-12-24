package br.com.alissonfernandes.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoVacanciesException extends  Exception{

    public NoVacanciesException(String str) {
        super(str);
    }
}

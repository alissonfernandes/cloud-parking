package br.com.alissonfernandes.cloudparking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDTO {

    HttpStatus httpStatus;
    String message;

}

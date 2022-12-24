package br.com.alissonfernandes.cloudparking.dto;

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

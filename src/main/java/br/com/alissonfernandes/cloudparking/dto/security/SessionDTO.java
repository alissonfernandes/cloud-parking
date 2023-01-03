package br.com.alissonfernandes.cloudparking.dto.security;

import lombok.Data;

@Data
public class SessionDTO {

    private String login;
    private String token;

}

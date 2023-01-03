package br.com.alissonfernandes.cloudparking.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class JWTObject {

    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;

}

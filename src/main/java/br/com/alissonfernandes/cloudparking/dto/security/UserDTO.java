package br.com.alissonfernandes.cloudparking.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private List<String> roles;
}

package br.com.alissonfernandes.cloudparking.controller.security;

import br.com.alissonfernandes.cloudparking.dto.security.UserDTO;
import br.com.alissonfernandes.cloudparking.service.security.SecurityDatabaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private SecurityDatabaseService securityService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return securityService.createUser(userDTO);
    }
}

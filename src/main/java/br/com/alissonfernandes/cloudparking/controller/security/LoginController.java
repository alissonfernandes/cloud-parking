package br.com.alissonfernandes.cloudparking.controller.security;

import br.com.alissonfernandes.cloudparking.dto.security.LoginDTO;
import br.com.alissonfernandes.cloudparking.dto.security.SessionDTO;
import br.com.alissonfernandes.cloudparking.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/login")
public class LoginController {

    private SecurityService securityService;

    @PostMapping
    public SessionDTO login(@RequestBody LoginDTO login) {
        return securityService.login(login);
    }
}

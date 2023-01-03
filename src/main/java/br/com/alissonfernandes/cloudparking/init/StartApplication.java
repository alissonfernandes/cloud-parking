package br.com.alissonfernandes.cloudparking.init;

import br.com.alissonfernandes.cloudparking.dto.security.UserDTO;
import br.com.alissonfernandes.cloudparking.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StartApplication implements CommandLineRunner {

    private SecurityService securityService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if(!securityService.exists("admin")){

            UserDTO user = new UserDTO();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("admin123");
            user.setRoles(List.of("MANAGERS","USERS"));
            UserDTO userSaved = securityService.createUser(user);
            System.out.println("\n" +
                    "NEW USER CREATED: \n\tUsername: admin \n\tPassword: admin123\n");
        }

    }
}
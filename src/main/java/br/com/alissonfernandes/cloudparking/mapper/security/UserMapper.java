package br.com.alissonfernandes.cloudparking.mapper.security;

import br.com.alissonfernandes.cloudparking.dto.security.UserDTO;
import br.com.alissonfernandes.cloudparking.model.security.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {

        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setRoles(user.getRoles());


        return userDTO;
    }

    public User toModel(UserDTO userDTO) {

        if (userDTO == null) return null;

        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());

        return user;
    }
}

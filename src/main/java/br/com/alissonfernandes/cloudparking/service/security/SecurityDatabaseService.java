package br.com.alissonfernandes.cloudparking.service.security;

import br.com.alissonfernandes.cloudparking.dto.security.UserDTO;
import br.com.alissonfernandes.cloudparking.mapper.security.UserMapper;
import br.com.alissonfernandes.cloudparking.model.security.User;
import br.com.alissonfernandes.cloudparking.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityDatabaseService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                authorities);
        return user;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User userModel = userMapper.toModel(userDTO);
        User userSaved =  userRepository.save(userModel);
        return userMapper.toDTO(userSaved);
    }
}
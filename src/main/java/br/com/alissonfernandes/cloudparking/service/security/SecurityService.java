package br.com.alissonfernandes.cloudparking.service.security;

import br.com.alissonfernandes.cloudparking.dto.security.LoginDTO;
import br.com.alissonfernandes.cloudparking.dto.security.SessionDTO;
import br.com.alissonfernandes.cloudparking.dto.security.UserDTO;
import br.com.alissonfernandes.cloudparking.mapper.security.UserMapper;
import br.com.alissonfernandes.cloudparking.model.security.User;
import br.com.alissonfernandes.cloudparking.repository.security.UserRepository;
import br.com.alissonfernandes.cloudparking.security.JWTCreator;
import br.com.alissonfernandes.cloudparking.security.JWTObject;
import br.com.alissonfernandes.cloudparking.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityService  implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder encoder;

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
        userModel.setPassword(encoder.encode(userDTO.getPassword()));// criptografando antes de salvar no banco
        User userSaved =  userRepository.save(userModel);
        return userMapper.toDTO(userSaved);
    }

    public SessionDTO login(LoginDTO login) {
        User user = userRepository.findByUsername(login.getUsername());
        if (user != null) {
            boolean passwordOK = encoder.matches(login.getPassword(), user.getPassword());

            if (!passwordOK) throw new RuntimeException("Error password: " + login.getPassword());

            SessionDTO sessao = new SessionDTO();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));

            return sessao;

        } else throw new RuntimeException("Error login");
    }
}
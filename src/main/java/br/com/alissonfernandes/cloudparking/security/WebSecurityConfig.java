package br.com.alissonfernandes.cloudparking.security;

import br.com.alissonfernandes.cloudparking.service.security.SecurityDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity // habilitando segurança web manual
@EnableGlobalMethodSecurity(prePostEnabled = true)//
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityDatabaseService securityService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] METHOD_GET_ROLE_USERS = {"/vacancy", "/all", "/motorcycle", "/car", "/unoccupied", "exit/", "vehicle/"};
        String[] METHOD_PUT_ROLE_MANAGERS = {"/vacancy/", "/vehicle/"};
        String[] METHOD_DELETE_ROLE_MANAGERS = {"/vacancy/", "/vehicle/"};

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, METHOD_GET_ROLE_USERS).hasRole("USERS")
                .antMatchers(HttpMethod.POST, "/entry").hasRole("USERS")
                .antMatchers(HttpMethod.POST, "/api/v1/vacancy").hasRole("MANAGERS")
                .antMatchers(HttpMethod.PUT, METHOD_PUT_ROLE_MANAGERS).hasRole("MANAGERS")
                .antMatchers(HttpMethod.DELETE, METHOD_DELETE_ROLE_MANAGERS).hasRole("MANAGERS")
                .anyRequest().authenticated().and().httpBasic();
    }

}

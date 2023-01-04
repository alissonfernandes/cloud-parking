package br.com.alissonfernandes.cloudparking.security;

import br.com.alissonfernandes.cloudparking.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // habilitando segurança web manual
@EnableGlobalMethodSecurity(prePostEnabled = true)//
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService securityService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    private static String[] AUTH_WHITELIST_SWAGGER = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] METHOD_GET_ROLE_USERS = {"/api/v1/vacancy", "/all", "/motorcycle", "/car", "/unoccupied", "exit/", "vehicle/"};
        String[] METHOD_PUT_ROLE_MANAGERS = {"/api/v1/vacancy/", "/api/v1//vehicle/"};
        String[] METHOD_DELETE_ROLE_MANAGERS = {"/api/v1/vacancy/", "/api/v1/vehicle/"};
        String[] METHOD_POST_ROLE_MANAGERS = {"/api/v1/vacancy"};



        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(this.AUTH_WHITELIST_SWAGGER).permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/register").hasRole("MANAGERS")
                .antMatchers(HttpMethod.GET, METHOD_GET_ROLE_USERS).hasRole("USERS")
                .antMatchers(HttpMethod.POST, "/entry").hasRole("USERS")
                .antMatchers(HttpMethod.POST, METHOD_POST_ROLE_MANAGERS).hasRole("MANAGERS")
                .antMatchers(HttpMethod.PUT, METHOD_PUT_ROLE_MANAGERS).hasRole("MANAGERS")
                .antMatchers(HttpMethod.DELETE, METHOD_DELETE_ROLE_MANAGERS).hasRole("MANAGERS")
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}

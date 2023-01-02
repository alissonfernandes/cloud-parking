package br.com.alissonfernandes.cloudparking.core;

import br.com.alissonfernandes.cloudparking.mapper.impl.VacancyMapperImpl;
import br.com.alissonfernandes.cloudparking.mapper.impl.VehicleMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigBean {

    @Bean
    public VacancyMapperImpl vacancyMapper() {
        return new VacancyMapperImpl();
    }

    @Bean
    public VehicleMapperImpl vehicleMapper() {
        return new VehicleMapperImpl();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

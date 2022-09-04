package br.com.alissonfernandes.cloudparking.core;

import br.com.alissonfernandes.cloudparking.mapper.impl.VacancyMapperImpl;
import br.com.alissonfernandes.cloudparking.mapper.impl.VehicleMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VacancyMapperConfig {

    @Bean
    public VacancyMapperImpl vacancyMapper() {
        return new VacancyMapperImpl();
    }

    @Bean
    public VehicleMapperImpl vehicleMapper() {
        return new VehicleMapperImpl();
    }
}

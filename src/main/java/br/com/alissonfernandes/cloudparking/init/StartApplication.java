package br.com.alissonfernandes.cloudparking.init;

import br.com.alissonfernandes.cloudparking.model.security.User;
import br.com.alissonfernandes.cloudparking.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
    }
}
package com.example.demo.student;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Logger;

@Configuration
@EnableNeo4jRepositories
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository, SKUGeneratorService skuGeneratorService) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    skuGeneratorService.generateSKUA()
            );

            Student alex = new Student(
                    2L,
                    "Alex",
                    "alex.jamal@gmail.com",
                    LocalDate.of(2001, Month.SEPTEMBER, 15),
                    skuGeneratorService.generateSKUA()
            );

            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}

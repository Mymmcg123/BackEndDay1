package com.example.demo.ProjectList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProjectRepository repository){
        return args -> {
            ProjectList projectList = new ProjectList(
                    "numer project",
                    "description",
                    5,
                    LocalDate.of(2021, Month.JUNE, 1)

            );

            ProjectList projectList2 = new ProjectList(
                    "numer project 2",
                    "description2",
                    3,
                    LocalDate.of(2020, Month.JUNE, 1)

            );
            repository.saveAll(
                    List.of(projectList,projectList2)
            );
        };
    }

}

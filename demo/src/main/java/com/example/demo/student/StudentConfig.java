package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student praveen  =  new Student(
                    "Praveen",
                    "praveen@duck.com",
                    LocalDate.of(2003, Month.MARCH, 31),
                    19);
            Student bobby  =  new Student(
                    "Bobby",
                    "bobby@duck.com",
                    LocalDate.of(2003, Month.MARCH, 27),
                    19);

            repository.saveAll(
                    List.of(praveen,bobby)
            );
        };
    }
}

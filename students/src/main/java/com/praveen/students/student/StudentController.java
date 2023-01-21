package com.praveen.students.student;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("api/v1/students")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class StudentController {
@GetMapping
    public List<Student> getAllStudents(){
        return List.of(
                new Student(
                        UUID.randomUUID(),
                        "Praveen",
                        "Varma",
                        "praveen@iecsemanipal.com",
                        Student.Gender.MALE),
                new Student(
                        UUID.randomUUID(),
                        "Sanjana",
                        "S",
                        "sanjana@rubrik.com",
                        Student.Gender.FEMALE)
                );
    }
}

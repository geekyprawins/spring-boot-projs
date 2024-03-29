package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
       Optional<Student> optionalStudent =  studentRepository.findStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);

    }


    public void deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId)){
            throw new IllegalStateException("student with id "+ studentId+ " does not exists.");
        }

        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("student with id "+ studentId+ " does not exists."));
        if(name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }

}

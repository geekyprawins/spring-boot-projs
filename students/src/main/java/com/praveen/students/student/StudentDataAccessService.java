package com.praveen.students.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static RowMapper<Student> getStudentRowMapper() {
        return (resultSet, rowNum) -> {
            String studentIdString = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdString);

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String genderString = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderString);

            return new Student(studentId, firstName, lastName, email, gender);
        };
    }

    public List<Student> selectAllStudents() {

        String sqlQuery = "" +
                "SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " gender " +
                "FROM student";


        return jdbcTemplate.query(sqlQuery, getStudentRowMapper());

    }
}

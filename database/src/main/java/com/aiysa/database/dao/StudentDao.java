package com.aiysa.database.dao;

import com.aiysa.database.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        String sql = """
                SELECT id, name, email
                FROM students
                ORDER BY id
                """;

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                )
        );
    }

    public int save(Student student) {
        String sql = """
                INSERT INTO students (name, email)
                VALUES (?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail()
        );
    }
}
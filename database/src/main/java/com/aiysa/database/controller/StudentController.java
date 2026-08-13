package com.aiysa.database.controller;

import com.aiysa.database.dao.StudentDao;
import com.aiysa.database.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentDao studentDao;

    @GetMapping
    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        studentDao.save(student);
        return "Student created successfully";
    }
}
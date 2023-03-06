package com.springbootTesting.springboottesting.controller;

import com.springbootTesting.springboottesting.model.Student;
import com.springbootTesting.springboottesting.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
}

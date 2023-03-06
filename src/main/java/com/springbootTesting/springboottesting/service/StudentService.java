package com.springbootTesting.springboottesting.service;

import com.springbootTesting.springboottesting.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
}

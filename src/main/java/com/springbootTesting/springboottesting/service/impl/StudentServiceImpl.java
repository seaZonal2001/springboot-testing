package com.springbootTesting.springboottesting.service.impl;

import com.springbootTesting.springboottesting.model.Student;
import com.springbootTesting.springboottesting.repository.StudentRepository;
import com.springbootTesting.springboottesting.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        Optional<Student> savedStudent = studentRepository.findByEmail(student.getEmail());
        if(savedStudent.isPresent()){
            throw new RuntimeException("Student Already Exist with given email");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

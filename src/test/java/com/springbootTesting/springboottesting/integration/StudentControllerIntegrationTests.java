package com.springbootTesting.springboottesting.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootTesting.springboottesting.model.Student;
import com.springbootTesting.springboottesting.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setup(){
        studentRepository.deleteAll();
    }

    @Test
    void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception{
        Student student = Student.builder()
                .firstName("Surjit")
                .lastName("Kausik")
                .email("surjitkausik@gmail.com")
                .build();

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(student.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",CoreMatchers.is(student.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",CoreMatchers.is(student.getEmail())));
    }

    @Test
    void givenStudentsList_whenGetAllStudents_thenReturnStudentsList() throws Exception{
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(Student.builder().firstName("Surjit").lastName("Kausik").email("surjitkausik@gmail.com").build());
        studentsList.add(Student.builder().firstName("Tijrus").lastName("Kisuak").email("tijruskisuak@gmail.com").build());

        studentRepository.saveAll(studentsList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/student"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(studentsList.size())));
    }
}

package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentControllerTest {


    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Mock
    private TestRestTemplate restTemplate;
    @Mock
    private StudentDTO dto;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentService service;
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    StudentDTO testStudent = new StudentDTO(1L, "Гарри", 15, 1);
    StudentDTO wrongTestStudent = new StudentDTO(25L, "Хагрид", 36, 0);

    @Test
    public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {
        mockMvc.perform(
                        post("/student")
                                .content(dto.toString(testStudent))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Michail"));
    }

    public StudentDTO createTestStudent(StudentDTO studentDTO) {
        return service.addStudent(studentDTO);
    }

    @Test
    public void givenId_whenGetExistingPerson_thenStatus200andPersonReturned() throws Exception {
        long id = createTestStudent(testStudent).getId();
        mockMvc.perform(
                        get("/student/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Michail"));
    }

    public void givenId_whenGetNotExistingPerson_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                        get("/student/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    @Test
    void createGetUpdateDeleteStudent() {
        StudentDTO student = new StudentDTO(1L, "test", 22);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isEqualTo(student);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find?id=1", Student.class))
                .isEqualTo(student);
        StudentDTO studentUpdated = new StudentDTO(1L, "test2", 22);
        this.restTemplate.put("http://localhost:" + port + "/student", studentUpdated);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find?id=1", Student.class))
                .isEqualTo(studentUpdated);
        this.restTemplate.delete("http://localhost:" + port + "/student/" + studentUpdated.getId());
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find?id=1", Student.class))
                .isNull();
    }

    @Test
    void getStudentsByAgeAndFindByAgeBetween() {
        StudentDTO student = new StudentDTO(1L, "test", 10);
        this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/filter?age=10", List.class))
                .isNotEmpty();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?age1=10&age2=11", List.class))
                .isNotEmpty();
        this.restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find?id=1", Student.class))
                .isNull();

    }

}

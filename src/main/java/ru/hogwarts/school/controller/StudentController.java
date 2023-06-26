package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService StudentService;

    public StudentController(StudentService StudentService) {
        this.StudentService = StudentService;
    }

    @GetMapping()
    public Collection<Student> getAllFaculties() {
        return this.StudentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Integer id) {
        return this.StudentService.getStudentById(id);
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student Student) {
        return this.StudentService.addStudent(Student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") long id, @RequestBody Student Student) {
        return StudentService.updateStudent(id, Student);
    }

    @DeleteMapping("/{id}")
    public Student removeStudent(@PathVariable("id") long id) {
        return StudentService.removeStudent(id);
    }
}

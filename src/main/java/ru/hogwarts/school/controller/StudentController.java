package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public Collection<Student> getAllFaculties() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("{age}")
    public List<Student> getStudentsByAge(@PathVariable("age") int age) {
        return this.studentService.getStudentsByAge(age);
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student Student) {
        return studentService.addStudent(Student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") long id, @RequestBody Student Student) {
        return studentService.updateStudent(id, Student);
    }

    @DeleteMapping("/{id}")
    public Student removeStudent(@PathVariable("id") long id) {
        return studentService.removeStudent(id);
    }


}

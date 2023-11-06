package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public Collection<StudentDTO> getAllFaculties() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public StudentDTO getStudent(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping()
    public StudentDTO addStudent(@RequestBody StudentDTO dto) {
        return studentService.addStudent(dto);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO dto) {
        return studentService.updateStudent(dto);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable("id") int id) {
        studentService.removeStudent(id);
    }

    @GetMapping()
    public List<StudentDTO> findByAgeBetween(int minAge, int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{name}")
    public FacultyDTO findStudentFaculty(@PathVariable String name) {
        return studentService.findFacultyByStudentName(name);
    }

    @GetMapping("/getStudentsByFaculty")
    public List<StudentDTO> getStudentsByIdFaculty(int id) {
        return studentService.getStudentsByIdFaculty(id);
    }
}

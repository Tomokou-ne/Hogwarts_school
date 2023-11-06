package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public StudentDTO getStudent(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping()
    public StudentDTO addStudent(@RequestBody StudentDTO dto) {
        return studentService.addStudent(dto);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable("id") long id, @RequestBody StudentDTO dto) {
        return studentService.updateStudent(dto);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable("id") long id) {
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

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @GetMapping("/getStudentsByFaculty")
    public List<StudentDTO> getStudentsByIdFaculty(long id) {
        return studentService.getStudentsByIdFaculty(id);
    }

    @GetMapping("/findAVGStudentAge")
    public Integer findAVGStudentAge() {
        return studentService.findAVGStudentAge();
    }

    @GetMapping("/findYoungerStudents")
    public List<Student> findYoungerStudents() {
        return studentService.findYoungerStudents();
    }

}

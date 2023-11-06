package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @GetMapping("/getStudentsByFaculty")
    public List<StudentDTO> getStudentsByIdFaculty(int id) {
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

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable int id, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable int id) {
        Avatar avatar = avatarService.findAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/{id}/avatar")
    public void downloadAvatar(@PathVariable int id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

}

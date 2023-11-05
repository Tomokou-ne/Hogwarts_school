package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Value("${application.avatars.folder}")
    private String avatarsDir;

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent (Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void removeStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        Optional<Student> findStudent = getStudentById(student.getId());
        if (findStudent == null) {
            throw new RuntimeException("Student not found");
        }
        return studentRepository.save(student);
    }

    public List<Student> getStudentsByAge(int age) {
        return studentRepository.getAllByAge(age);
    }

    public List<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Student updateImage(int studentId, MultipartFile multipartFile) {
        Student student = getStudentById(studentId);
        student.(imageService.saveUserImage(multipartFile));
        return save(userEntity);
    }
}

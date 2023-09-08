package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //get all students
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //add new student to hashmap
    public Student addStudent (Student student) {
        return studentRepository.save(student);
    }

    //read object from hashmap
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    //student expulsion
    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    //update student-information
    public Student updateStudent(Student student) {
        Optional<Student> findStudent = getStudentById(student.getId());
        if (findStudent == null) {
            throw new RuntimeException("Student not found");
        }
        return studentRepository.save(student);
    }

    //get students by age
    public List<Student> getStudentsByAge(int age) {
        return studentRepository.getAllByAge(age);
    }

    public List<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }
}

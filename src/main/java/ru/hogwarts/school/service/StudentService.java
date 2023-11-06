package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.hogwarts.school.dto.StudentDTO.fromStudent;
import static ru.hogwarts.school.dto.FacultyDTO.fromFaculty;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student toStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAge(),
                facultyRepository.findById(studentDTO.getFacultyId()));
    }

    public List<StudentDTO> getAllStudents(Integer pageNumber, Integer pageSize) {
        if (pageSize > 50 || pageSize <= 0) {
            pageSize = 50;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }

    public StudentDTO addStudent (StudentDTO studentDTO) {
        Student student = toStudent(studentDTO);
        studentRepository.save(student);
        return studentDTO;
    }
    public StudentDTO getStudentById(long id) {
        return fromStudent(studentRepository.findById(id));
    }

    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO updateStudent(StudentDTO dto) {
        Student student = toStudent(dto);
        studentRepository.save(student);
        return dto;
    }

    public List<StudentDTO> findByAgeBetween(int minAge, int maxAge) {
        List<Student> studentByAgeBetween = studentRepository.findByAgeBetween(minAge, maxAge);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : studentByAgeBetween) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public FacultyDTO findFacultyByStudentName(String studentName) {
        Student currentStudent = studentRepository.findStudentByNameIgnoreCase(studentName);
        FacultyDTO faculty = fromFaculty(currentStudent.getFaculty());
        return faculty;
    }

    public List<StudentDTO> getStudentsByIdFaculty(long id) {
        List<Student> studentByFacultyId = studentRepository.findStudentByFacultyId(id);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : studentByFacultyId) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public Integer findAVGStudentAge() {
        return studentRepository.findAVGStudentAge();
    }

    public List<Student> findYoungerStudents() {
        return studentRepository.findYoungerStudents();
    }

}

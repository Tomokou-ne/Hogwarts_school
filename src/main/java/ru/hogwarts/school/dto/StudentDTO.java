package ru.hogwarts.school.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hogwarts.school.model.Student;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private long facultyId;

    public StudentDTO(Long id, String name, int age, long facultyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
    }

    public StudentDTO(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        return dto;
    }

    public String toString(StudentDTO student) {
        return student.id + " " + student.name + " " + student.age + " " + student.facultyId;
    }
}

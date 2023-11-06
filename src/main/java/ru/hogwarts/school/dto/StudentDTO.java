package ru.hogwarts.school.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hogwarts.school.model.Student;

@RequiredArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private long facultyId;

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        return dto;
    }

}

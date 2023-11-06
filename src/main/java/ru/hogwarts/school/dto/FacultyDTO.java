package ru.hogwarts.school.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hogwarts.school.model.Faculty;

@RequiredArgsConstructor
@Getter
@Setter
public class FacultyDTO {
    private Long id;
    private String name;
    private String color;

    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public static Faculty toFaculty(FacultyDTO dto) {
        return new Faculty(dto.getId(), dto.getName(), dto.getColor());
    }
}

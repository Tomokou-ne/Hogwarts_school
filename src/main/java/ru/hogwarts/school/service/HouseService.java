package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

import static ru.hogwarts.school.dto.FacultyDTO.toFaculty;
import static ru.hogwarts.school.dto.FacultyDTO.fromFaculty;
@Service
public class HouseService {

    private FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO addFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = toFaculty(facultyDTO);
        return fromFaculty(facultyRepository.save(faculty));
    }

    public FacultyDTO getFacultyById(long id) {
        return fromFaculty(facultyRepository.findById(id));
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = toFaculty(facultyDTO);
        facultyRepository.save(faculty);
        return facultyDTO;
    }

    public List<FacultyDTO> getFacultyByColor(String color) {
        List<Faculty> allByColorIgnoreCase = facultyRepository.findByColorIgnoreCase(color);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();

        for (Faculty faculty : allByColorIgnoreCase) {
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }
}

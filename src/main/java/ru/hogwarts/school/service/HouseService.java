package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class HouseService {

    private FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty addFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyById(long id) {
        return facultyRepository.findById(id);
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty (Faculty faculty) {
        Optional<Faculty> findFaculty = getFacultyById(faculty.getId());
        if (findFaculty == null) {
            throw new RuntimeException("Faculty not found");
        }
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findByColor(color);
    }

}

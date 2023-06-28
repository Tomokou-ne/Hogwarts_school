package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {

    private long counter = 0;

    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    //get all faculties
    public Collection<Faculty> getAllFaculties() {
        return facultyMap.values();
    }

    //add new faculty to hashmap
    public Faculty addFaculty (Faculty faculty) {
        facultyMap.put(this.counter++, faculty);
        return faculty;
    }

    //read object from hashmap
    public Faculty getFacultyById(long id) {
        return facultyMap.get(id);
    }

    //delete object
    public Faculty removeFaculty(long id) {
        if (this.facultyMap.containsKey(id)) {
            return this.facultyMap.remove(id);
        }
        return null;
    }

    //update faculty-information
    public Faculty updateFaculty (long id, Faculty faculty) {
        if (facultyMap.containsKey(id)) {
            return facultyMap.put(id, faculty);
        }
        return null;
    }

    //get faculties by color
    public List<Faculty> getFacultiesByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .toList();
    }

}

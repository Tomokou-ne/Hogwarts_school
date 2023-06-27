package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping()
    public Collection<Faculty> getAllFaculties() {
        return houseService.getAllFaculties();
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Integer id) {
        return houseService.getFacultyById(id);
    }

    @GetMapping("{color}")
    public List<Faculty> getFacultiesByColor(@PathVariable("color") String color) {
        return houseService.getFacultiesByColor(color);
    }

    @PostMapping()
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return houseService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
        return houseService.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty removeFaculty(@PathVariable("id") long id) {
        return houseService.removeFaculty(id);
    }
}

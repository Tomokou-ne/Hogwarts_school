package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping()
    public Collection<Faculty> getAllFaculties() {
        return this.houseService.getAllFaculties();
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Integer id) {
        return this.houseService.getFacultyById(id);
    }

    @PostMapping()
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return this.houseService.addFaculty(faculty);
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

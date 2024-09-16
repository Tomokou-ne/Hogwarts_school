package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public FacultyDTO getFaculty(@PathVariable long id) {
        return houseService.getFacultyById(id);
    }

    @GetMapping("{color}")
    public List<FacultyDTO> getFacultiesByColor(@PathVariable("color") String color) {
        return houseService.getFacultyByColor(color);
    }

    @PostMapping()
    public FacultyDTO addFaculty(@RequestBody FacultyDTO dto) {
        return houseService.addFaculty(dto);
    }

    @PutMapping("/{id}")
    public FacultyDTO updateFaculty(@PathVariable("id") long id, @RequestBody FacultyDTO dto) {
        return houseService.updateFaculty(dto);
    }

    @DeleteMapping("/{id}")
    public void removeFaculty(@PathVariable("id") long id) {
        houseService.removeFaculty(id);
    }
}

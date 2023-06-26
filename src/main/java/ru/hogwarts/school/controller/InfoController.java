package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class InfoController {

    @GetMapping("/info")
    public String info() {
        return "Project's author is Bychkova Margarita " + "/n" +
                "Name: SockStoreApp " + "/n" +
                "Date of creation: 26/03/2023" + "/n" +
                "Description: app ";
    }
}

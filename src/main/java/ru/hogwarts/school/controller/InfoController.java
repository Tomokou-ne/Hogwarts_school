package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.dto.AppInfoDTO;
import ru.hogwarts.school.service.AppInfoService;

@RestController
@RequestMapping("/appInfo")
public class InfoController {

    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }


    @GetMapping()
    public ResponseEntity<AppInfoDTO> getAppInfo() {
        AppInfoDTO appInfo = appInfoService.getAppInfo();
        return ResponseEntity.ok(appInfo);
    }
}

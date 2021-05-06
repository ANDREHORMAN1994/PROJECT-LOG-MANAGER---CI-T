package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.entity.Level;
import com.javabugs.logmanager.service.interfaces.LevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/level")
public class LevelController {

    private final LevelService levelService;

    public LevelController(final LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public ResponseEntity<List<Level>> getAllLevel() {
        List<Level> result = this.levelService.findAll();
        return new ResponseEntity<List<Level>>(result, HttpStatus.OK);
    }
}

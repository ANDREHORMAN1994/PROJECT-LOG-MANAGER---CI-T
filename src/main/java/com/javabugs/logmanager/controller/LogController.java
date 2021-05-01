package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.dto.LogDto;
import com.javabugs.logmanager.entity.Level;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.service.interfaces.LevelService;
import com.javabugs.logmanager.service.interfaces.LogService;
import com.javabugs.logmanager.service.interfaces.OriginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService logService;
    private final LevelService levelService;
    private final OriginService originService;

    public LogController(final LogService logService, final LevelService levelService, final OriginService originService) {
        this.logService = logService;
        this.levelService = levelService;
        this.originService = originService;
    }

    @PostMapping
    public ResponseEntity<LogDto> createLog(@Valid @RequestBody LogDto logDto) {
        Log newLog = new Log();
        newLog.setDate(logDto.getDate());
        newLog.setDescription(logDto.getDescription());
        newLog.setEvent(logDto.getEvent());
        newLog.setQuantity(logDto.getQuantity());
        newLog.setLevel(this.levelService.findByName(logDto.getLevel()));
        newLog.setOrigin(this.originService.findByName(logDto.getOrigin()));

        this.logService.save(newLog);
        logDto.setId(newLog.getId());

        return new ResponseEntity<LogDto>(logDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Level> findIdLevelbyName(@RequestParam("name") String name) {
        return new ResponseEntity<Level>(this.levelService.findByName(name), HttpStatus.OK);
    }

}

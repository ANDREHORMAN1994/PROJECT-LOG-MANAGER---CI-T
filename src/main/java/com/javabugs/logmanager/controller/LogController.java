package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.controller.advice.LogConstraintViolationException;
import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.mappers.LogMapperImpl;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private LogService logService;
    private LogMapperImpl logMapper;

    public LogController(LogService logService, LogMapperImpl logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @PostMapping
    public ResponseEntity<LogDTO> createLog(@Valid @RequestBody LogDTO logDTO) {
        Log log = logMapper.toLog(logDTO);

        if (log.getLevel() == null) throw new LogConstraintViolationException("Level");
        if (log.getOrigin() == null) throw new LogConstraintViolationException("Origin");

        this.logService.save(log);
        return new ResponseEntity<LogDTO>(logMapper.toLogDTO(log), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs(

            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String OrderBy) {
        List<Log> result;
        switch (filterType.toLowerCase()) {
            case "date":
                result = this.logService.findByDate(filter);
                break;
            case "description":
                result = this.logService.findByDescription(filter);
                break;
            case "event":
                result = this.logService.findByEvent(filter);
                break;
            case "quantity":
                result = this.logService.findByQuantity(filter);
                break;
            case "level":
                result = this.logService.findByLevel(filter);
                break;
            case "origin":
                result = this.logService.findByOrigin(filter);
                break;
            default:
                result = this.logService.findAll();
        }
        return new ResponseEntity<List<LogDTO>>(logMapper.toLogDTO(result), HttpStatus.OK);
    }

}

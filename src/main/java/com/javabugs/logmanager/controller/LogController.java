package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.controller.advice.LogConstraintViolationException;
import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.mappers.LogMapperImpl;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
            @RequestParam(required = false) String OrderBy,
            @RequestParam(required = true) Integer page) {

        List<Log> result;
        String filterTypeLowerCase = new String();

        if (filterType != null) {
            filterTypeLowerCase = filterType.toLowerCase();
        }

        Pageable pageable = PageRequest.of(page -1, 3);

        switch (filterTypeLowerCase) {
            case "date":
                result = this.logService.findByDate(filter, pageable);
                break;
            case "description":
                result = this.logService.findByDescription(filter, pageable);
                break;
            case "event":
                result = this.logService.findByEvent(filter, pageable);
                break;
            case "quantity":
                result = this.logService.findByQuantity(Integer.parseInt(filter), pageable);
                break;
            case "level":
                result = this.logService.findByLevel(filter, pageable);
                break;
            case "origin":
                result = this.logService.findByOrigin(filter, pageable);
                break;
            default:
                result = this.logService.findAll(pageable);
        }

        return new ResponseEntity<List<LogDTO>>(logMapper.toLogDTO(result), HttpStatus.OK);
    }

}

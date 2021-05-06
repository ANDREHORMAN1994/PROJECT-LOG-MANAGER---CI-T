package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.controller.advice.LogException;
import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.mappers.LogMapperImpl;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

        if (log.getLevel() == null) throw new LogException("Level not Found");
        if (log.getOrigin() == null) throw new LogException("Origin not Found");

        this.logService.save(log);
        return new ResponseEntity<LogDTO>(logMapper.toLogDTO(log), HttpStatus.CREATED);
    }

    public List<Log> filterType(String filterTypeLowerCase, String filter, Pageable pageable) {
        List<Log> result;
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
        return result;
    }

    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs(
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String OrderBy,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page) {

        String filterTypeLowerCase = "";
        Integer sizeValue = size;
        Integer pageValue = page;

        if (filterType != null) filterTypeLowerCase = filterType.toLowerCase();
        if (size == null) sizeValue = 5;
        if (page == null) pageValue = 1;

        Pageable pageable = PageRequest.of(pageValue -1, sizeValue);
        List<Log> result = filterType(filterTypeLowerCase, filter, pageable);

        return new ResponseEntity<List<LogDTO>>(logMapper.toLogDTO(result), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LogDTO> getLogById(@PathVariable("id") Long id) {
        Optional<Log> logOptional = logService.findById(id);

        if (!logOptional.isPresent()) throw new LogException("Log not Found");
        Log result = logOptional.get();

        return new ResponseEntity<LogDTO>(logMapper.toLogDTO(result), HttpStatus.OK);
    }

}

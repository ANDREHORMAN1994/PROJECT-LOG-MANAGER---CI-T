package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.controller.advice.LogException;
import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.mappers.LogMapperImpl;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Log> filterType(String filterTypeLowerCase, String filterValue, Pageable pageable) {
        List<Log> result;
        switch (filterTypeLowerCase) {
            case "date":
                result = this.logService.findByDate(filterValue, pageable);
                break;
            case "description":
                result = this.logService.findByDescription(filterValue, pageable);
                break;
            case "event":
                result = this.logService.findByEvent(filterValue, pageable);
                break;
            case "quantity":
                result = this.logService.findByQuantity(Integer.parseInt(filterValue), pageable);
                break;
            case "level":
                result = this.logService.findByLevel(filterValue, pageable);
                break;
            case "origin":
                result = this.logService.findByOrigin(filterValue, pageable);
                break;
            default:
                result = this.logService.findAll(pageable);
        }
        return result;
    }

    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs(
            @RequestParam(required = false) String filterField,
            @RequestParam(required = false) String filterValue,
            @RequestParam(required = false) String orderField,
            @RequestParam(defaultValue = "asc") String orderDir,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "1") Integer page) {

        String filterTypeLowerCase = "";
        if (filterField != null) filterTypeLowerCase = filterField.toLowerCase();

        Sort sort;
        Pageable pageable;
        if (orderField != null) {
            sort = orderDir.equals("asc")
                ? Sort.by(orderField).ascending()
                : Sort.by(orderField).descending();
            pageable = PageRequest.of(page -1, size, sort);
        } else {
            pageable = PageRequest.of(page -1, size);
        }

        List<Log> result = filterType(filterTypeLowerCase, filterValue, pageable);
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

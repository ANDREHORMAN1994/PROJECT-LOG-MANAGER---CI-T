package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.controller.advice.LogException;
import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.dto.LogWithoutEventDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.mappers.LogMapperImpl;
import com.javabugs.logmanager.service.interfaces.LogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;

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
    @ApiOperation("Cria um Log de Erro")
    @ApiResponse(code = 400, message = "Not possible create this Log")
    public ResponseEntity<LogDTO> createLog(@Valid @RequestBody LogDTO logDTO) {
        Log log = logMapper.toLog(logDTO);

        if (log.getLevel() == null) throw new LogException("Level not Found");
        if (log.getOrigin() == null) throw new LogException("Origin not Found");

        this.logService.save(log);
        return new ResponseEntity<LogDTO>(logMapper.toLogDTO(log), HttpStatus.CREATED);
    }

    public Page<Log> filterType(String filterTypeLowerCase, String filterValue, Pageable pageable) {
        Page<Log> result;
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
    @ApiOperation("Filtra todos Logs de Erro por campo")
    @ApiResponse(code = 404, message = "Log Not Found")
    public Page<LogWithoutEventDTO> getAllLogs(
            @RequestParam(required = false) String filterField,
            @RequestParam(required = false) String filterValue,
            @PageableDefault(page = 0, size = 20, sort = "date", direction = Sort.Direction.DESC ) Pageable pageable) {

        String filterTypeLowerCase = "";
        if (filterField != null) filterTypeLowerCase = filterField.toLowerCase();

        Page<Log> logs = filterType(filterTypeLowerCase, filterValue, pageable);
        Page<LogWithoutEventDTO> pageDTO = logs.map(new Function<Log, LogWithoutEventDTO>() {
            @Override
            public LogWithoutEventDTO apply(Log log) {
                return logMapper.toLogWithoutEventDTO(log);
            }
        });
        return pageDTO;
    }

    @GetMapping("{id}")
    @ApiOperation("Filtra um Log de Erro pelo Id")
    @ApiResponse(code = 404, message = "Log Not Found")
    public ResponseEntity<Log> getLogById(@PathVariable("id") Long id) {
        Optional<Log> logOptional = logService.findById(id);

        if (!logOptional.isPresent()) throw new LogException("Log not Found");
        Log result = logOptional.get();

        return new ResponseEntity<Log>(result, HttpStatus.OK);
    }

}

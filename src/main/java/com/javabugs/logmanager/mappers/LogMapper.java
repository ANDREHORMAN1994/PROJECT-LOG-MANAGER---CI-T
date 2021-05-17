package com.javabugs.logmanager.mappers;

import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.dto.LogWithoutEventDTO;
import com.javabugs.logmanager.entity.Log;

import com.javabugs.logmanager.service.interfaces.LevelService;
import com.javabugs.logmanager.service.interfaces.OriginService;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
abstract class LogMapper {
    private LevelService levelService;
    private OriginService originService;

    public LogMapper(LevelService levelService, OriginService originService) {
        this.levelService = levelService;
        this.originService = originService;
    }

    public LogDTO toLogDTO(Log log) {
        LogDTO logDTO = new LogDTO();
        logDTO.setId(log.getId());
        logDTO.setDate(log.getDate());
        logDTO.setDescription(log.getDescription());
        logDTO.setEvent(log.getEvent());
        logDTO.setQuantity(log.getQuantity());
        logDTO.setLevel(log.getLevel().getName());
        logDTO.setOrigin(log.getOrigin().getName());

        return logDTO;
    }
    public abstract List<LogDTO> toLogDTO(Collection<Log> logs);

    public LogWithoutEventDTO toLogWithoutEventDTO(Log log) {
        LogWithoutEventDTO logDTO = new LogWithoutEventDTO();
        logDTO.setId(log.getId());
        logDTO.setDate(log.getDate());
        logDTO.setDescription(log.getDescription());
        logDTO.setQuantity(log.getQuantity());
        logDTO.setLevel(log.getLevel().getName());
        logDTO.setOrigin(log.getOrigin().getName());

        return logDTO;
    }

    public Log toLog(LogDTO logDTO) {
        Log log = new Log();
        log.setDate(logDTO.getDate());
        log.setDescription(logDTO.getDescription());
        log.setEvent(logDTO.getEvent());
        log.setQuantity(logDTO.getQuantity());
        log.setLevel(this.levelService.findByName(logDTO.getLevel()));
        log.setOrigin(this.originService.findByName(logDTO.getOrigin()));
        return log;
    }

    public abstract List<Log> toLog(Collection<LogDTO> logDTOs);
}

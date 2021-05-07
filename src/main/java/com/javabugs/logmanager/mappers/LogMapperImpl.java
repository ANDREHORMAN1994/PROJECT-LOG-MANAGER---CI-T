package com.javabugs.logmanager.mappers;

import com.javabugs.logmanager.dto.LogDTO;
import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.service.interfaces.LevelService;
import com.javabugs.logmanager.service.interfaces.OriginService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class LogMapperImpl extends LogMapper {

    public LogMapperImpl(LevelService levelService, OriginService originService) {
        super(levelService, originService);
    }

    @Override
    public List<LogDTO> toLogDTO(Collection<Log> logs) {
        if ( logs == null ) {
            return null;
        }

        List<LogDTO> list = new ArrayList<>();
        for ( Log log : logs ) {
            list.add( toLogDTO( log ) );
        }

        return list;
    }

    @Override
    public List<Log> toLog(Collection<LogDTO> logDTOs) {
        if ( logDTOs == null ) {
            return null;
        }

        List<Log> list = new ArrayList<>();
        for ( LogDTO logDTO : logDTOs ) {
            list.add( toLog( logDTO ) );
        }

        return list;
    }
}

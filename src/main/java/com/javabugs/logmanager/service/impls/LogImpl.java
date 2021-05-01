package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.repository.LogRepository;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogImpl implements LogService {

    private final LogRepository logRepository;

    public LogImpl(final LogRepository logRepository) {
        this.logRepository = logRepository;
    }



    @Override
    public void save(Log log) {
        logRepository.save(log);
    }

}

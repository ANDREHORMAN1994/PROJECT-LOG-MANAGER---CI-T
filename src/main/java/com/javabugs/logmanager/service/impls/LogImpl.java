package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.repository.LogRepository;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findByDate(String filter) {
        return logRepository.findByDate(filter);
    }
    @Override
    public List<Log> findByDescription(String filter) {
        return logRepository.findByDescription(filter);
    }

    @Override
    public List<Log> findByEvent(String filter) {
        return logRepository.findByEvent(filter);
    }

    @Override
    public List<Log> findByQuantity(String filter) {
        return logRepository.findByQuantity(filter);
    }

    @Override
    public List<Log> findByLevel(String filter) {
        return logRepository.findByLevel(filter);
    }

    @Override
    public List<Log> findByOrigin(String filter) {
        return logRepository.findByOrigin(filter);
    }
}

package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.repository.LogRepository;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
    public List<Log> findAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

    @Override
    public List<Log> findByDate(String filter, Pageable pageable) {
        return logRepository.findByDate(filter, pageable);
    }

    @Override
    public List<Log> findByDescription(String filter, Pageable pageable) {
        return logRepository.findByDescription(filter, pageable);
    }

    @Override
    public List<Log> findByEvent(String filter, Pageable pageable) {
        return logRepository.findByEvent(filter, pageable);
    }

    @Override
    public List<Log> findByQuantity(Integer filter, Pageable pageable) {
        return logRepository.findByQuantity(filter, pageable);
    }

    @Override
    public List<Log> findByLevel(String filter, Pageable pageable) {
        return logRepository.findByLevel(filter, pageable);
    }

    @Override
    public List<Log> findByOrigin(String filter, Pageable pageable) {
        return logRepository.findByOrigin(filter, pageable);
    }
}

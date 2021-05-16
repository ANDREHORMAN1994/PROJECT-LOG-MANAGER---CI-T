package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Log;
import com.javabugs.logmanager.repository.LogRepository;
import com.javabugs.logmanager.service.interfaces.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogImpl implements LogService {

    private final LogRepository logRepository;

    public LogImpl(final LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(final Log log) {
        logRepository.save(log);
    }

    @Override
    public Page<Log> findAll(final Pageable pageable) {
        return logRepository.findAllDate(pageable);
    }

    @Override
    public Page<Log> findByDate(final String filter, final Pageable pageable) {
        return logRepository.findByDate(filter, pageable);
    }

    @Override
    public Page<Log> findByDescription(final String filter, final Pageable pageable) {
        return logRepository.findByDescription(filter, pageable);
    }

    @Override
    public Page<Log> findByEvent(final String filter, final Pageable pageable) {
        return logRepository.findByEvent(filter, pageable);
    }

    @Override
    public Page<Log> findByQuantity(final Integer filter, final Pageable pageable) {
        return logRepository.findByQuantity(filter, pageable);
    }

    @Override
    public Page<Log> findByLevel(final String filter, final Pageable pageable) {
        return logRepository.findByLevel(filter, pageable);
    }

    @Override
    public Page<Log> findByOrigin(final String filter, final Pageable pageable) {
        return logRepository.findByOrigin(filter, pageable);
    }

    @Override
    public Optional<Log> findById(final Long id) {
        return logRepository.findById(id);
    }

}

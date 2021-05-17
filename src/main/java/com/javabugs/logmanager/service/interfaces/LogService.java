package com.javabugs.logmanager.service.interfaces;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LogService {
  
    void save(Log log);
    Page<Log> findAll(Pageable pageable);
    Page<Log> findByDate(String filter, Pageable pageable);
    Page<Log> findByDescription(String filter, Pageable pageable);
    Page<Log> findByEvent(String filter, Pageable pageable);
    Page<Log> findByQuantity(Integer filter, Pageable pageable);
    Page<Log> findByLevel(String filter, Pageable pageable);
    Page<Log> findByOrigin(String filter, Pageable pageable);
    Optional<Log> findById(Long id);

}

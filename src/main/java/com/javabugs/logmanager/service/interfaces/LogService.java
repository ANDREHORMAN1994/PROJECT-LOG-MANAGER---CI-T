package com.javabugs.logmanager.service.interfaces;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LogService {
  
    void save(Log log);
    List<Log> findAll();
    List<Log> findByDate(String filter);
    List<Log> findByDescription(String filter);
    List<Log> findByEvent(String filter);
    List<Log> findByQuantity(Integer filter);
    List<Log> findByLevel(String filter);
    List<Log> findByOrigin(String filter);

}



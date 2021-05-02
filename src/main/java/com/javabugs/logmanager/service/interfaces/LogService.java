package com.javabugs.logmanager.service.interfaces;

import com.javabugs.logmanager.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogService {

    void save(Log log);

    List<Log> findAll();

}



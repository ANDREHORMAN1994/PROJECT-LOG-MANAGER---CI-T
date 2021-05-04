package com.javabugs.logmanager.service.interfaces;

import com.javabugs.logmanager.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> findAll();
    Level findByName(String name);
}

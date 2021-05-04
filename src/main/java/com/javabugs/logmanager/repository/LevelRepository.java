package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends CrudRepository<Level, Long> {
    public List<Level> findAll();
    public Level findByName(String name);
}

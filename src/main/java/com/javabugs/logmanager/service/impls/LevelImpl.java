package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Level;
import com.javabugs.logmanager.repository.LevelRepository;
import com.javabugs.logmanager.service.interfaces.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelImpl implements LevelService {
    private final LevelRepository levelRepository;
    public LevelImpl(final LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }
    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }
    @Override
    public Level findByName(String name) {
        return levelRepository.findByName(name);
    }
}

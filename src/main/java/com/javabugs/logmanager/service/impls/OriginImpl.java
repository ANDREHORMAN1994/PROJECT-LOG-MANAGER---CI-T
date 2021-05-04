package com.javabugs.logmanager.service.impls;

import com.javabugs.logmanager.entity.Origin;
import com.javabugs.logmanager.repository.OriginRepository;
import com.javabugs.logmanager.service.interfaces.OriginService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginImpl implements OriginService {
    private final OriginRepository originRepository;
    public OriginImpl(final OriginRepository originRepository) {
        this.originRepository = originRepository;
    }
    @Override
    public List<Origin> findAll() {
        return originRepository.findAll();
    }
    @Override
    public Origin findByName(final String name) {
        return originRepository.findByName(name);
    }
}

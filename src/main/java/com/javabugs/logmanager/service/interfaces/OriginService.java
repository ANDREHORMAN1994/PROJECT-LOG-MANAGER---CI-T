package com.javabugs.logmanager.service.interfaces;

import com.javabugs.logmanager.entity.Level;
import com.javabugs.logmanager.entity.Origin;

import java.util.List;

public interface OriginService {

    List<Origin> findAll();

    Origin findByName(String name);

}

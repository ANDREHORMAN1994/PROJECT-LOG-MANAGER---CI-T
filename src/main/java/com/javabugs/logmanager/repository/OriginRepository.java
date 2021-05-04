package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Origin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginRepository extends CrudRepository<Origin, Long> {
    public List<Origin> findAll();
    public Origin findByName(final String name);
}

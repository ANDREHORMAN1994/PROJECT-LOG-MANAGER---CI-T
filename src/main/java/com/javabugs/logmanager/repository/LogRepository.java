package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

    public List<Log> findAll();

}

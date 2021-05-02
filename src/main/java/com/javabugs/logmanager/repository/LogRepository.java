package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

    public List<Log> findAll();
    @Query(value = "SELECT * FROM logs WHERE date = :filter", nativeQuery = true)
    public List<Log> findByDate(String filter);
    @Query(value = "SELECT * FROM logs WHERE description = :filter", nativeQuery = true)
    public List<Log> findByDescription(String filter);
    @Query(value = "SELECT * FROM logs WHERE event = :filter", nativeQuery = true)
    public List<Log> findByEvent(String filter);
    @Query(value = "SELECT * FROM logs WHERE quantity = :filter", nativeQuery = true)
    public List<Log> findByQuantity(String filter);
    @Query(value = "SELECT * FROM logs WHERE level = :filter", nativeQuery = true)
    public List<Log>findByLevel(String filter);
    @Query(value = "SELECT * FROM logs WHERE origin = :filter", nativeQuery = true)
    public List<Log>findByOrigin(String filter);

}

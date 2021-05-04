package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    public List<Log> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE date = :filter", nativeQuery = true)
    public List<Log> findByDate(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE description = :filter", nativeQuery = true)
    public List<Log> findByDescription(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE event = :filter", nativeQuery = true)
    public List<Log> findByEvent(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE quantity = :filter", nativeQuery = true)
    public List<Log> findByQuantity(@Param("filter") Integer filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE level = :filter", nativeQuery = true)
    public List<Log>findByLevel(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE origin = :filter", nativeQuery = true)
    public List<Log>findByOrigin(@Param("filter") String filter, Pageable pageable);

}

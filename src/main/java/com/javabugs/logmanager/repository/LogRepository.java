package com.javabugs.logmanager.repository;

import com.javabugs.logmanager.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT * FROM log", nativeQuery = true)
    Page<Log> findAllDate(Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE date = :filter", nativeQuery = true)
    Page<Log> findByDate(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE description = :filter", nativeQuery = true)
    Page<Log> findByDescription(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE event = :filter", nativeQuery = true)
    Page<Log> findByEvent(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM log WHERE quantity = :filter", nativeQuery = true)
    Page<Log> findByQuantity(@Param("filter") Integer filter, Pageable pageable);

    @Query(value = "SELECT * FROM log l INNER JOIN level le ON l.level_id = le.id WHERE le.name = :filter", nativeQuery = true)
    Page<Log>findByLevel(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT l.* FROM log l INNER JOIN origin o ON l.origin_id = o.id WHERE o.name = :filter", nativeQuery = true)
    Page<Log>findByOrigin(@Param("filter") String filter, Pageable pageable);

}

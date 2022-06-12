package com.ege.todoservice.repository;

import com.ege.todoservice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todo WHERE target_date >= :startDate AND target_date <= :endDate", nativeQuery = true)
    List<Todo> findAllByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Todo> findAllByUserId(Long userId);
}

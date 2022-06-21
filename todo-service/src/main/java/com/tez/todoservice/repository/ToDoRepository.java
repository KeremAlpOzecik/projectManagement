package com.tez.todoservice.repository;

import com.tez.todoservice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todo WHERE date >= :startDate AND date <= :endDate AND user_id = :userId", nativeQuery = true)
    List<Todo> findAllByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Long userId);

    List<Todo> findAllByUserId(Long userId);

    List<Todo> findAllByUserIdAndCompleted(Long userId, Boolean completed);
}

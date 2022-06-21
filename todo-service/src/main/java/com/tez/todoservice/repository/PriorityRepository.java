package com.tez.todoservice.repository;

import com.tez.todoservice.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findAllByUserId(Long userId);
}

package com.ege.todoservice.repository;

import com.ege.todoservice.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findAllByUserId(Long userId);
}

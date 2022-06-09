package com.taskify.projectservice.dao;

import com.taskify.projectservice.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
Project findByProjectName(String projectName);
List<Project> findByUserId(Long userId);
List<Project> findAllByProjectEndDate(LocalDate projectEndDate);
}

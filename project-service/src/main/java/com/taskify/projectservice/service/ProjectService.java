package com.taskify.projectservice.service;

import com.taskify.projectservice.ApiResponse;
import com.taskify.projectservice.User;
import com.taskify.projectservice.UserFeignClient;
import com.taskify.projectservice.dao.ProjectRepository;
import com.taskify.projectservice.entities.Project;
import com.taskify.projectservice.entities.Task;
import com.taskify.projectservice.entities.event.CreateNotificationDto;
import com.taskify.projectservice.entities.event.CreateNotificationEvent;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final UserFeignClient userFeignClient;
    private final ApplicationEventPublisher publisher;
@Transactional()
    public ResponseEntity<ApiResponse<Project>> createProject(Project project) {
        log.info("Entered" + getClass().getName());
        String transactionId = UUID.randomUUID().toString();
        Project project1 = null;
        User user = getUserDetails(project);
        project1 = project;
        if (user != null)
            project1.setUserId(user.getId());

        ResponseEntity<ApiResponse<Project>> responseEntity = new ResponseEntity<>(
                new ApiResponse<>(projectRepository.save(project), new Date(), "Project Created"),
                HttpStatus.CREATED);
    CreateNotificationDto createNotificationDto = new CreateNotificationDto();
    createNotificationDto.setEmail(user.getEmail());
    createNotificationDto.setDate(new Date());
    createNotificationDto.setProject(project1.toString());
    publishTransactionNotification(createNotificationDto,transactionId);
        return responseEntity;
    }
    public User getUserDetails(Project project) {
        return userFeignClient.getUserByUserName(project.getUserName()).getBody().getData();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
    public List<Task> getProjectTasks(Long id) {
        return taskService.getTasksByProjectId(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public List<Project> getAllProjectsByUserId(Long id) {
        return projectRepository.findByUserId(id);
    }

    public Project updateProject(Project project,Long id) {
        //updadte project
        projectRepository.findById(id).ifPresent(p -> { projectRepository.save(project);});
        return project;}
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void publishTransactionNotification(CreateNotificationDto createNotificationDto, String transactionId) {
        CreateNotificationEvent event = new CreateNotificationEvent(transactionId, createNotificationDto, new Date());
        publisher.publishEvent(event);
    }
    @Transactional()
    @Scheduled(fixedRate = 3600000)
    public void notifyUserEndDateComing(){
        log.info("Entered" + getClass().getName());
        List<Project> projects = projectRepository.findAll();
        for(Project project:projects){
            if(LocalDateTime.now().toLocalDate().plusDays(7).isAfter(project.getProjectEndDate()
            )&&!( LocalDateTime.now().toLocalDate().isAfter(project.getProjectEndDate()))){
                log.info("Project End Date is coming");
                log.info("USER NAME,{}",project.getUserName());
                CreateNotificationDto createNotificationDto = new CreateNotificationDto();
                User data = userFeignClient.getUserByUserName(project.getUserName()).getBody().getData();
                createNotificationDto.setEmail(data.getEmail());
                createNotificationDto.setDate(new Date());
                createNotificationDto.setProject(project.toString());
                publishTransactionNotification(createNotificationDto,"");
            }
        }
    }

    }


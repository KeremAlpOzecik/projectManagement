package com.taskify.projectservice.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})



public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userName;
    private String projectName;
    private String projectDescription;
    private String projectStatus;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate projectStartDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate projectEndDate;


}

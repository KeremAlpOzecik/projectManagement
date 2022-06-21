package com.micro.calendar.controller;

import com.micro.calendar.entity.Task;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "project-service")
@LoadBalancerClient(name = "project-service")
public interface ProjectFeignClient {

    @GetMapping("/v1/api/project/task/all/userid")
   List<Task> getAllTasksByUserId(@RequestParam("userid") Long userid);
}

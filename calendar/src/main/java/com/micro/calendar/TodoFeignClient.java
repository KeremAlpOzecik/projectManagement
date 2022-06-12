package com.micro.calendar;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "todo-service")
@LoadBalancerClient(name = "todo-service")
public interface TodoFeignClient {
    @GetMapping("/v1/api/todo/getAllByUserId/{userId}")
    List<TodoDto> getAllByUserId(@RequestParam("userId") Long userId);

}

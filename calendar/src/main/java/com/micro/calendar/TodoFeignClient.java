package com.micro.calendar;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "todo-service")
@LoadBalancerClient(name = "todo-service")
public interface TodoFeignClient {
    @GetMapping("/v1/api/todo/getAllByUserId/{userId}")
    ResponseEntity<ApiResponse<List<TodoDto>>> getAllByUserId(@PathVariable Long userid);

}

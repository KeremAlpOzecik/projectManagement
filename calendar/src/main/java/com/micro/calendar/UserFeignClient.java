package com.micro.calendar;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
@LoadBalancerClient(name = "user-service")
public interface UserFeignClient {


    @GetMapping("/v1/api/users/username/{username}")
    ResponseEntity<ApiResponse<User>> getUserByUserName(@PathVariable String username);

    @GetMapping("/v1/api/users/{userid}")
    ResponseEntity<ApiResponse<User>> getUserByUserId(@PathVariable Long userid);


}

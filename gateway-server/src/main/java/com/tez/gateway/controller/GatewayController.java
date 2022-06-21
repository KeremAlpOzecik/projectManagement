package com.tez.gateway.controller;

import com.tez.gateway.model.ApiResponseAuth;
import com.tez.gateway.model.ApiResponse;
import com.tez.gateway.model.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tez.gateway.model.User;

@RequestMapping("/v1/api")
public interface GatewayController {
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> signUp(@RequestBody User user);

	@PostMapping("/login")
	public ResponseEntity<ApiResponseAuth<String>> login(@RequestBody LoginDetails loginDetails);

}

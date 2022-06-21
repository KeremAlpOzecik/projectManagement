package com.tez.gateway.service;

import com.tez.gateway.model.ApiResponseAuth;
import com.tez.gateway.model.ApiResponse;
import com.tez.gateway.model.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.tez.gateway.model.User;

public interface GatewayService {
	public ResponseEntity<ApiResponse<User>> signUp(@RequestBody User user);

	public ResponseEntity<ApiResponseAuth<String>> login(LoginDetails loginDetails);

}

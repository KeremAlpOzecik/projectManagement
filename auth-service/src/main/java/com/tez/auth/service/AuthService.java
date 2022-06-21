package com.tez.auth.service;

import com.tez.auth.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

	ResponseEntity<ApiResponse<UserDto>> signUp(@RequestBody User user);

	ResponseEntity<ApiResponseAuth<String>> login(LoginDetails loginDetails);

	Boolean validateToken(String token);
}

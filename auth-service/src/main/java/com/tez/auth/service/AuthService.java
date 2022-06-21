package com.tez.auth.service;

import com.epam.auth.model.*;
import com.tez.auth.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

	public ResponseEntity<ApiResponse<UserDto>> signUp(@RequestBody User user);

	public ResponseEntity<ApiResponseAuth<String>> login(LoginDetails loginDetails);

	public Boolean validateToken(String token);
}

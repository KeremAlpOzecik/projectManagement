package com.tez.auth.controller;

import com.epam.auth.model.*;
import com.tez.auth.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api")
public interface AuthController {
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<UserDto>> signUp(@RequestBody User user);

	@PostMapping("/login")
	public ResponseEntity<ApiResponseAuth<String>> login(@RequestBody LoginDetails loginDetails);

	@GetMapping("/validate/{token}")
	public Boolean validateToken(@PathVariable String token);
}

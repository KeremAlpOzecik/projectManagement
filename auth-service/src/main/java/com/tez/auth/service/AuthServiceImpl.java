package com.tez.auth.service;

import java.util.Date;

import com.epam.auth.model.*;
import com.tez.auth.model.*;
import com.tez.auth.utility.UserFeignClient;
import com.tez.auth.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@CircuitBreaker(name = "user-service", fallbackMethod = "loginFallback")
	public ResponseEntity<ApiResponseAuth<String>> login(LoginDetails loginDetails) {
		log.info("Sending the get call to the guest service to get the user details");
		User user = userFeignClient.getUserByUserName(loginDetails.getUserName()).getBody().getData();
		log.info("User detials has retrieved ");
		String userName = null;
		if (user != null) {


			if (!passwordEncoder.matches(loginDetails.getPassword(),user.getPassword())) {
				throw new RuntimeException("Password is wrong");
			}
			userName = user.getPassword();
		}
		return new ResponseEntity<>(
				new ApiResponseAuth<>(user.getId(),jwtUtility.generateToken(userName), new Date(), "Token generated"),
				HttpStatus.CREATED);
	}

	@Override
	public Boolean validateToken(String token) {

		return jwtUtility.isTokenExpired(token);
	}

	public ResponseEntity<ApiResponse<String>> loginFallback(LoginDetails loginDetails, Exception e) {

		return new ResponseEntity<>(new ApiResponse<>("user-service is down", new Date(), e.getMessage()),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> signUp(User userDto) {
		User user = userFeignClient.addUser(userDto).getBody().getData();
		UserDto dto = new UserDto();
		dto.setStatus(user.getStatus());
		dto.setUserName(user.getUserName());
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());

		return new ResponseEntity<>(new ApiResponse<>(dto, new Date(), "Token generated"), HttpStatus.CREATED);
	}

}

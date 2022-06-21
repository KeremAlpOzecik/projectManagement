package com.tez.auth.controller;

import com.epam.auth.model.*;
import com.tez.auth.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tez.auth.service.AuthService;

@RestController
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<ApiResponseAuth<String>> login(LoginDetails loginDetails) {
        return authService.login(loginDetails);
    }

    @Override
    public Boolean validateToken(String token) {

        return authService.validateToken(token);
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> signUp(User user) {
      return authService.signUp(user);

    }

}

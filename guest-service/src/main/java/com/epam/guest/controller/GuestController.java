package com.epam.guest.controller;

import com.epam.guest.dto.ApiResponse;
import com.epam.guest.dto.ChangePasswordDto;
import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/api")
public interface GuestController {
	@PostMapping("/users")
	public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto userDto);

	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<UserDto>>> getUsers();

	@GetMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long userid);

	@PutMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable Long userid);

	@DeleteMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> deleteUserById(@PathVariable Long userid);

	@PutMapping("/users/changepassword")
	public String changePassword(@RequestBody ChangePasswordDto changePasswordDto);

	@GetMapping("/users/username/{username}")
	public ResponseEntity<ApiResponse<UserDto>> getUserByUserName(@PathVariable String username);

	@GetMapping("/users/details")
	public UserDetails getUserDetailsById(@RequestParam("id") Long id );
}

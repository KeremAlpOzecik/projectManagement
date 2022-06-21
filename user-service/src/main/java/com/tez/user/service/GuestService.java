package com.tez.user.service;

import java.util.List;

import com.tez.user.dto.ChangePasswordDto;
import com.tez.user.dto.UserDto;
import com.tez.user.entity.User;
import com.tez.user.entity.UserDetails;

public interface GuestService {

	public User addUser(UserDto userDto);

	public List<User> getUsers();

	public User getUserById(Long userId);

	public User updateUser(UserDto userDto, Long userId);

	public String deleteUser(Long userId);

	public User getUserByUserName(String username);
	public void changePassword(ChangePasswordDto changePasswordDto);
	public UserDetails getUserDetails(Long id);
	public UserDetails setUserDetails(UserDetails userDetails);

}

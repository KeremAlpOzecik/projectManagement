package com.epam.guest.service;

import java.util.List;

import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;

public interface GuestService {

	public User addUser(UserDto userDto);

	public List<User> getUsers();

	public User getUserById(Long userId);

	public User updateUser(UserDto userDto, Long userId);

	public String deleteUser(Long userId);

	public User getUserByUserName(String username);

}

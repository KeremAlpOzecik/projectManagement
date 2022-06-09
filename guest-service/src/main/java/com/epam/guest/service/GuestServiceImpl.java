package com.epam.guest.service;

import java.util.List;
import java.util.Optional;

import com.epam.guest.mapper.GuestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;
import com.epam.guest.exception.UserNotFoundException;
import com.epam.guest.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;

	public User addUser(UserDto userDto) {
		return guestRepository.save(GuestMapper.INSTANCE.convert(userDto));
	}

	public List<User> getUsers() {
		return guestRepository.findByStatus(true);
	}

	public User getUserById(Long userId) {
		Optional<User> optionalUser = guestRepository.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Unble to find the User");
		}
		return optionalUser.get();
	}

	public User updateUser(UserDto userDto, Long userId) {
		User userEntiry = GuestMapper.INSTANCE.convert(userDto);
		User user = getUserById(userId);

		user.setStatus(userEntiry.getStatus());


		return guestRepository.save(user);
	}

	public String deleteUser(Long userId) {// return deleted/updated User
		guestRepository.deleteById(userId);
		return "User" + userId + "has updated";
	}

	@Override
	public User getUserByUserName(String username) {
		Optional<User> optionalUser = guestRepository.findByUserName(username);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Unble to find the User");
		}
		return optionalUser.get();
	}

}

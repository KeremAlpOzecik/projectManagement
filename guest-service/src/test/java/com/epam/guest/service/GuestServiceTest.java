package com.epam.guest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.guest.mapper.GuestMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;
import com.epam.guest.exception.UserNotFoundException;
import com.epam.guest.repository.GuestRepository;

class GuestServiceTest {

	@Mock
	private GuestRepository guestRepository;

	@InjectMocks
	private GuestServiceImpl guestService;


	private User user;
	private UserDto userDto;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		userDto = new UserDto();
		userDto.setStatus(true);


		user = GuestMapper.INSTANCE.convert(userDto);

	}

	@Test
	void addUserTest() {
		Mockito.when(guestRepository.save(user)).thenReturn(user);
		User userEntity = guestService.addUser(userDto);

		Assertions.assertAll(() -> assertNotNull(userEntity),
				() -> assertEquals(userEntity.getStatus(), userDto.getStatus()));

	}

	@Test
	void getUsersTest() {
		UserDto userDto = new UserDto();
		userDto.setStatus(true);



		List<User> users = new ArrayList<>();
		users.add(GuestMapper.INSTANCE.convert(userDto));

		Mockito.when(guestRepository.findByStatus(true)).thenReturn(users);

		List<User> actualUsers = guestService.getUsers();
		Assertions.assertNotNull(actualUsers);
		Assertions.assertTrue(actualUsers.size() > 0);

	}

	@Test
	void getUserById() {
		User user = GuestMapper.INSTANCE.convert(userDto);
		Optional<User> optionalUser = Optional.of(user);
		Mockito.when(guestRepository.findById(ArgumentMatchers.anyLong())).thenReturn(optionalUser);

		User actualUser = guestService.getUserById(1L);

		Assertions.assertAll(() -> assertNotNull(actualUser),
				() -> assertEquals(actualUser.getStatus(), userDto.getStatus()));
	}

	@Nested
	@DisplayName("Updated user test case")
	class UpdateUserTest {
		@Test
		void updateUserTest() {
			Optional<User> optionalUser = Optional.of(user);
			Mockito.when(guestRepository.findById(ArgumentMatchers.anyLong())).thenReturn(optionalUser);

			User userEntity = guestService.getUserById(1L);

		}

		@Test
		void guestNotFoundException() {

			Optional<User> optionalUser = Optional.of(user);
			Mockito.when(guestRepository.findById(1L)).thenReturn(optionalUser);

			User userEntity = guestService.getUserById(1l);

			Mockito.when(guestRepository.save(userEntity)).thenReturn(userEntity);

			Assertions.assertThrows(UserNotFoundException.class, () -> guestService.getUserById(100L));
		}
	}

}

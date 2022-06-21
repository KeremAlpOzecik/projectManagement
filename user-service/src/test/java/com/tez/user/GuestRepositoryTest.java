package com.tez.user;

import com.tez.user.dto.UserDto;
import com.tez.user.entity.User;
import com.tez.user.mapper.GuestMapper;
import com.tez.user.repository.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestRepositoryTest {

	@Autowired
	private GuestRepository guestRepository;

	private UserDto userDto;


	@BeforeEach
	void beforeEach() {

		userDto = new UserDto();
		userDto.setStatus(true);


	}

	@Test
	void validateDataSource() {
		User user = guestRepository.save(GuestMapper.INSTANCE.convert(userDto));
		Assertions.assertNotNull(user);
	}
}

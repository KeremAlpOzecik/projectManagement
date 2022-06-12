package com.epam.guest.service;

import java.util.List;
import java.util.Optional;

import com.epam.guest.dto.ChangePasswordDto;
import com.epam.guest.entity.UserDetails;
import com.epam.guest.exception.UserPasswordNotMatchException;
import com.epam.guest.mapper.GuestMapper;
import com.epam.guest.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;
import com.epam.guest.exception.UserNotFoundException;
import com.epam.guest.repository.GuestRepository;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsRepository userDetailsRepository;

	public User addUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
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

	@Override
	public void changePassword(ChangePasswordDto changePasswordDto) {
		User user = getUserById(changePasswordDto.getId());
		if (!passwordEncoder.matches(changePasswordDto.getOldPassword(),user.getPassword())){
			throw new UserPasswordNotMatchException("Şifre Doğru Değil");
		}
		else  {
			user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
			guestRepository.save(user);
		}

	}

	@Override
	public UserDetails getUserDetails(Long id) {
		Optional<UserDetails> optionalUser = userDetailsRepository.findByUserId(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Unable to find the User");
		}
		return optionalUser.get();

	}
	private Boolean isUserExist(Long id){
		Optional<UserDetails> optionalUser = userDetailsRepository.findByUserId(id);
		return optionalUser.isPresent();

	}

	@Override
	public UserDetails setUserDetails(UserDetails userDetails) {
		if (Boolean.TRUE.equals(isUserExist(userDetails.getUserId()))){
			throw new IllegalArgumentException("User exist ");

		}
		return userDetailsRepository.save(userDetails);
	}


}

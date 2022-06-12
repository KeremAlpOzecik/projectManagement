package com.epam.guest.repository;

import java.util.List;
import java.util.Optional;

import com.epam.guest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.guest.entity.User;
@Repository
public interface GuestRepository extends JpaRepository<User,Long> {
	public List<User> findByStatus(Boolean status);
	public Optional<User> findByUserName(String username);
}

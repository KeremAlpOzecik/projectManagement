package com.tez.user.repository;

import java.util.List;
import java.util.Optional;

import com.tez.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<User,Long> {
	public List<User> findByStatus(Boolean status);
	public Optional<User> findByUserName(String username);
}

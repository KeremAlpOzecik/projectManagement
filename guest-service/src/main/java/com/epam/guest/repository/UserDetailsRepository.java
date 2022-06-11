package com.epam.guest.repository;

import com.epam.guest.entity.User;
import com.epam.guest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
Optional<UserDetails> findByUserId(Long userId);
}

package com.epam.guest.repository;

import com.epam.guest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

}

package com.greencity.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greencity.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByEmail(String email);

	User findByContactNumber(int contactNumber);

	Optional<User> findBycontactNumber(int contactNumber);

}

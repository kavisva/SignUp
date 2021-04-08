package com.demo.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.signup.model.Login;

@Repository
public interface RegisterUserLoginRepository extends JpaRepository<Login, Long> {

	@Query(value = "select count(*) from login_details where username = ?1", nativeQuery = true)
	Integer fetchLoginCountUsername(String username);

}

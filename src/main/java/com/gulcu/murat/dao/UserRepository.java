package com.gulcu.murat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gulcu.murat.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}

package com.gulcu.murat.service;

import com.gulcu.murat.dto.UserDto;

public interface UserService {

	UserDto findByUsername(String username);

	UserDto findById(int id);
}

package com.gulcu.murat.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gulcu.murat.dao.UserRepository;
import com.gulcu.murat.dto.RegistrationRequest;
import com.gulcu.murat.dto.UserDto;
import com.gulcu.murat.entities.User;
import com.gulcu.murat.service.UserService;

@Service
public class UserServiceImpl implements UserService{

		private final UserRepository userDao;
	    private final ModelMapper modelMapper;
	    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	    public UserServiceImpl(UserRepository userDao, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userDao = userDao;
	        this.modelMapper = modelMapper;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    @Transactional
	    public Boolean register (RegistrationRequest registrationRequest){
	            User user = new User();
	            user.setName(registrationRequest.getName());
	            user.setSurname(registrationRequest.getSurname());
	            user.setUsername(registrationRequest.getUsername());
	            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
	            user.setEmail(registrationRequest.getEmail());
	            this.userDao.save(user);
	            return Boolean.TRUE;
	    }

	    @Override
	    public UserDto findByUsername(String username) {
	        User user = this.userDao.findByUsername(username);
	        UserDto userDto = modelMapper.map(user,UserDto.class);
	        return userDto;
	    }

	    @Override
	    public UserDto findById(int id) {
	        User user = this.userDao.getOne(id);
	        UserDto userDto = modelMapper.map(user,UserDto.class);
	        return userDto;
	    }

}

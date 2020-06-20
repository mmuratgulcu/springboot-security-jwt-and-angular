package com.gulcu.murat.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gulcu.murat.dto.UserDto;
import com.gulcu.murat.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/byId/{id}")
    public UserDto findById(@PathVariable("id") int id){
        return this.userServiceImpl.findById(id);
    }
  
}

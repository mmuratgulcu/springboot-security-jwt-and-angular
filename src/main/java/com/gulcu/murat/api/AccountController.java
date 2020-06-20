package com.gulcu.murat.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.gulcu.murat.dao.UserRepository;
import com.gulcu.murat.dto.LoginRequest;
import com.gulcu.murat.dto.RegistrationRequest;
import com.gulcu.murat.dto.TokenResponse;
import com.gulcu.murat.entities.User;
import com.gulcu.murat.security.JwtTokenUtil;
import com.gulcu.murat.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userDao;

    public AccountController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepository userDao) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
    }

    @PostMapping("/singIn")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        System.out.println("burada");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("burada");
        final User user = userDao.findByUsername(loginRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(),token));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        Boolean response = userServiceImpl.register(registrationRequest);
        return ResponseEntity.ok(response);
    }


}

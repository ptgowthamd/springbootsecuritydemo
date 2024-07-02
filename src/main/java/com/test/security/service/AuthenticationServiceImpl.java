package com.test.security.service;

import com.test.security.entity.UserInfo;
import com.test.security.exceptions.AuthException;
import com.test.security.exceptions.CustomException;
import com.test.security.model.AuthRequest;
import com.test.security.model.TokenResponse;
import com.test.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepository repository;

    @Override
    public String authenticateAndGetToken(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getUsername());
            } else {
                throw new AuthException("Authentication failed: User is not authenticated.");
            }
        } catch (AuthenticationException e) {
            throw new AuthException("Unauthorized: Invalid username or password.");
        }
    }

    @Override
    public String addUser(UserInfo userInfo) {
        try {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "user added to system ";
        }catch (Exception e){
            throw new CustomException("Failed to add user (may be duplicate user)");
        }

    }
}


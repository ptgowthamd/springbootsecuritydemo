package com.test.security.service;

import com.test.security.entity.UserInfo;
import com.test.security.model.AuthRequest;

public interface AuthenticationService {
    public String authenticateAndGetToken(AuthRequest authRequest);
    public String addUser(UserInfo userInfo);
}

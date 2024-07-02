package com.test.security.config;

import com.test.security.entity.UserInfo;
import com.test.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserInfoUserDetails(userInfo);
    }
}

package com.test.security.repository;

import com.test.security.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {
    UserInfo findByName(String username);
}
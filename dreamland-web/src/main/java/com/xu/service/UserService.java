package com.xu.service;

import com.xu.entity.User;

public interface UserService {
    int regist(User user);
    User login(String email,String password);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findById(Long id);
    void deleteByEmail(String email);
    void update(User user);
}

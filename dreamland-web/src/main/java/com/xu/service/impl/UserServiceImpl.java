package com.xu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xu.dao.UserMapper;
import com.xu.entity.User;
import com.xu.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public int regist(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        user.setEmail( email );
        user.setPassword( password );
        return userMapper.selectOne( user );
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        user.setEmail( email );
        return userMapper.selectOne( user );
    }

    @Override
    public User findByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        return userMapper.selectOne(user);
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }
    @Transactional
    @Override
    public void deleteByEmail(String email) {
        User user = new User();
        user.setEmail( email );
        userMapper.delete( user );
    }
    @Transactional
    @Override
    public void update(User user) {
        // TODO Auto-generated method stub
        userMapper.updateByPrimaryKeySelective( user );
    }
}

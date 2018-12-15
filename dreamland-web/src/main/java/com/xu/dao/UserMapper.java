package com.xu.dao;

import org.springframework.stereotype.Repository;

import com.xu.entity.User;

import tk.mybatis.mapper.common.Mapper;

//@Mapper
@Repository
public interface UserMapper extends Mapper<User>{

    @Override
    int insert(User record);

    @Override
    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(User record);

    @Override
    int updateByPrimaryKey(User record);
}
package com.xu.dao;

import com.xu.entity.UserInfo;

import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<UserInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
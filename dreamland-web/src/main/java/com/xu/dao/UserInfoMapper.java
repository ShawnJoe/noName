package com.xu.dao;

import org.springframework.stereotype.Repository;

import com.xu.entity.UserInfo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan
@Repository
public interface UserInfoMapper extends Mapper<UserInfo>{
//    int deleteByPrimaryKey(Long id);
//
//    int insert(UserInfo record);
//
//    int insertSelective(UserInfo record);
//
//    UserInfo selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(UserInfo record);
//
//    int updateByPrimaryKey(UserInfo record);
}
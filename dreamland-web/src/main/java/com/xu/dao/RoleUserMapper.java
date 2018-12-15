package com.xu.dao;

import com.xu.entity.RoleUser;

import tk.mybatis.mapper.common.Mapper;

public interface RoleUserMapper extends Mapper<RoleUser>{
    int deleteByPrimaryKey(Long id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);
}
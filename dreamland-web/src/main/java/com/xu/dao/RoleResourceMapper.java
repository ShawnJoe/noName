package com.xu.dao;

import com.xu.entity.RoleResource;
import com.xu.entity.RoleUser;

import tk.mybatis.mapper.common.Mapper;

public interface RoleResourceMapper extends Mapper<RoleResource>{
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}
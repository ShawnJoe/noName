package com.xu.dao;

import com.xu.entity.Comment;

import tk.mybatis.mapper.common.Mapper;

public interface CommentMapper extends Mapper<Comment>{
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}
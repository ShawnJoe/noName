package com.xu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.xu.dao.UserContentMapper;
import com.xu.dao.UserMapper;
import com.xu.entity.Comment;
import com.xu.entity.UserContent;
import com.xu.service.UserContentService;

@Service
public class UserContentServiceImpl implements UserContentService{
	@Autowired
    private UserContentMapper userContentMapper;
	
	@Override
	public Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize) {
		return null;
	}

	@Override
	public Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserContent> findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContent> findByUserId(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

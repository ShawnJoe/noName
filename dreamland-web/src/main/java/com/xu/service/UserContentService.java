package com.xu.service;

import com.github.pagehelper.Page;
import com.xu.entity.Comment;
import com.xu.entity.UserContent;

public interface UserContentService {
    Page<UserContent>  findAll(UserContent content, Integer pageNum, Integer pageSize);
    Page<UserContent>  findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize);
    Page<UserContent>  findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize);
}

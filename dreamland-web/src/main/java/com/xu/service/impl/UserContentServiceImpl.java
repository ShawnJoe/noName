package com.xu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.PageHelper;
import com.xu.common.PageHelper.Page;
import com.xu.dao.CommentMapper;
import com.xu.dao.UserContentMapper;
import com.xu.dao.UserMapper;
import com.xu.entity.Comment;
import com.xu.entity.UserContent;
import com.xu.service.UserContentService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserContentServiceImpl implements UserContentService{
	@Autowired
    private UserContentMapper userContentMapper;
    @Autowired
    private CommentMapper commentMapper;
	

	@Override
	public List<UserContent> select(UserContent userContent) {
		return userContentMapper.select(userContent);
	}


	;
	    public void addContent(UserContent content) {
	        userContentMapper.insert( content );
	    }

	    public List<UserContent> findByUserId(Long uid) {
	        UserContent userContent = new UserContent();
	        userContent.setuId(uid);
	        List<UserContent> list = userContentMapper.select( userContent );
	        return list;
	    }

	    public List<UserContent> findAll() {
	        return userContentMapper.select( null );
	    }

	    public Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize) {
	        //分页查询
	        System.out.println("第"+pageNum+"页");
	        System.out.println("每页显示："+pageSize+"条");
	        PageHelper.startPage(pageNum, pageSize);//开始分页
	        List<UserContent> list =  userContentMapper.select( content );
	        Page endPage = PageHelper.endPage();//分页结束
	        return endPage;
	    }

	    public Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize) {
	        //分页查询
	        System.out.println("第"+pageNum+"页");
	        System.out.println("每页显示："+pageSize+"条");
	        PageHelper.startPage(pageNum, pageSize);//开始分页
	        List<UserContent> list =  userContentMapper.select( content );

	        List<Comment> comments = commentMapper.select( comment );

	        Page endPage = PageHelper.endPage();//分页结束
	        List<UserContent> result = endPage.getResult();
	        return endPage;
	    }

	    public Page<UserContent> findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize) {
	        Example e = new Example(UserContent.class);
	        e.setOrderByClause("upvote DESC");
	        PageHelper.startPage(pageNum, pageSize);//开始分页
	        List<UserContent> list = userContentMapper.selectByExample(e);
	        Page endPage = PageHelper.endPage();//分页结束
	        return endPage;
	    }

	    public UserContent findById(long id) {
	        UserContent userContent = new UserContent();
	        userContent.setId( id );
	        return userContentMapper.selectOne( userContent );
	    }


	    public void updateById(UserContent content) {
	        userContentMapper.updateByPrimaryKeySelective( content );
	    }

}

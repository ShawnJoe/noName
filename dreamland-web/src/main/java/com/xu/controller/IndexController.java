package com.xu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xu.common.PageHelper;
import com.xu.common.PageHelper.Page;
import com.xu.dao.UserContentMapper;
import com.xu.entity.UserContent;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Autowired
	UserContentMapper userContentMapper;
	
	@RequestMapping("/init")
	@ResponseBody
	public Page indexInit() {
		PageHelper.startPage(null, null);//开始分页
        List<UserContent> list =  userContentMapper.select( null );
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
	    return endPage;
	}
    
}

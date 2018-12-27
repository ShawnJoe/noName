package com.xu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xu.entity.UserContent;
import com.xu.service.UserContentService;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Autowired
	UserContentService userContentServiceImpl;
	
	@RequestMapping("/init")
	@ResponseBody
	public Page indexInit() {
	    PageHelper.startPage(0, 7);//开始分页
	    PageHelper.orderBy("id DESC");
	    List<UserContent> list =  userContentServiceImpl.select(null);
	    PageInfo<UserContent> info = new PageInfo(list);
	    System.out.println(list.toString());
	    Page engPage = new Page();
	    return engPage;
	}
    
}

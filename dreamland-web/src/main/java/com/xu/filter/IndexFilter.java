package com.xu.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xu.dao.UserContentMapper;
import com.xu.entity.UserContent;
import com.xu.service.UserContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//@WebFilter(filterName = "sessionFilter",urlPatterns = {"/index.html"})
public class IndexFilter implements Filter{
	@Autowired
	private UserContentService userContentServiceImpl;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("===========自定义过滤器==========");
        ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        UserContentMapper userContentMapper = ctx.getBean(UserContentMapper.class);
        PageHelper.startPage(0, 7);//开始分页
	    PageHelper.orderBy("id DESC");
	    List<UserContent> list =  userContentServiceImpl.select(null);
	    PageInfo<UserContent> endPage = new PageInfo<UserContent>(list);
	    endPage.setIsFirstPage(true);
	    System.out.println(list.toString());
        request.setAttribute("page", endPage );
        chain.doFilter(request, response);
	}

}

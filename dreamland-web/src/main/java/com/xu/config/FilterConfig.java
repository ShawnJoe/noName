package com.xu.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.xu.filter.IndexFilter;

@Configurable
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<IndexFilter> IndexFilter() {
	    FilterRegistrationBean<IndexFilter> filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setOrder(6);
	    filterRegistrationBean.setFilter(new IndexFilter());
	    filterRegistrationBean.setName("IndexFilter");
	    filterRegistrationBean.addUrlPatterns("/dreamland/index.html");
	    return filterRegistrationBean;
	}
}

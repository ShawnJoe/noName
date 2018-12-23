package com.xu.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xu.common.MD5Util;
import com.xu.entity.User;
import com.xu.service.UserService;
import com.xu.constant.Constants;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
	private final static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
    private UserService userService;

	@RequestMapping("/doLogin")
	 public String doLogin(Model model, @RequestParam(value = "username",required = false) String email,
             @RequestParam(value = "password",required = false) String password,
             @RequestParam(value = "code",required = false) String code,
             @RequestParam(value = "state",required = false) String state,
             @RequestParam(value = "pageNum",required = false) Integer pageNum ,
             @RequestParam(value = "pageSize",required = false) Integer pageSize) {
		if (StringUtils.isBlank(code)) {
			model.addAttribute("error", "fail");
			return "../login";
		}
		int b = checkValidateCode(code);
		if (b == -1) {
			model.addAttribute("error", "fail");
			return "../login";
		} else if (b == 0) {
			model.addAttribute("error", "fail");
			return "../login";
		}
		password = MD5Util.encodeToHex(Constants.SALT+password);
		User user =  userService.login(email,password);
		if (user!=null){
			if("0".equals(user.getState())){
				//未激活
				model.addAttribute("email",email);
				model.addAttribute("error","active");
				return "../login";
			}
			log.info("用户登录登录成功");
			model.addAttribute("user",user);
			return "/personal/personal";
		}else{
			log.info("用户登录登录失败");
			model.addAttribute("email",email);
			model.addAttribute( "error","fail" );
			return "../login";
		}
	}

// 匹对验证码的正确性
	public int checkValidateCode(String code) {
		Object vercode = getRequest().getSession().getAttribute("VERCODE_KEY");
		if (null == vercode) {
			return -1;
		}
		if (!code.equalsIgnoreCase(vercode.toString())) {
			return 0;
		}
		return 1;
	}
}

package com.xu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xu.common.CodeCaptchaServlet;
import com.xu.common.MD5Util;
import com.xu.constant.RegisterCode;
import com.xu.entity.User;
import com.xu.mail.SendEmail;
import com.xu.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	private final static Logger log = Logger.getLogger(RegisterController.class);
    public static final String VERCODE_KEY = "VERCODE_KEY";
    @Autowired
    private UserService userService;
    @Autowired// redis数据库操作模板
    private RedisTemplate<String, String> redisTemplate;
    @RequestMapping("/codeCaptcha")
    public void CodeCaptcha(HttpSession session,HttpServletResponse response) {
        //        session.removeAttribute(LOGIN_VERCODE_KEY);
        session.removeAttribute(VERCODE_KEY);
        //        // 首先设置页面不缓存
        //        response.setHeader("Pragma", "No-cache");
        //        response.setHeader("Cache-Control", "no-cache");
        //        response.setDateHeader("Expires", 0);
        //
        //        //在内存中创建图象
        int iWidth = 55, iHeight = 18;
        BufferedImage image = new BufferedImage(iWidth, iHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //设定背景色
        g.setColor(Color.white);
        g.fillRect(0, 0, iWidth, iHeight);
        //画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, iWidth - 1, iHeight - 1);
        //取随机产生的认证码(4位数字)
        //String rand = Utils.getCharacterAndNumber(4);
        int intCount=0; 
        intCount=(new Random()).nextInt(9999);// 

        if(intCount <1000)intCount+=1000; 
        String rand=intCount+"";
        //将认证码显示到图象中
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.drawString(rand, 5, 15);
        //随机产生88个干扰点,使图象中的认证码不易被其它程序探测到
        Random random = new Random();
        for (int iIndex = 0; iIndex < 100; iIndex++) {
            int x = random.nextInt(iWidth);
            int y = random.nextInt(iHeight);
            g.drawLine(x, y, x, y);
        }

        // 将生成的随机字符串写入session
        // request.getSession().setAttribute(LOGIN_VERCODE_KEY, rand);
        session.setAttribute(VERCODE_KEY, rand);
        //图象生效
        g.dispose();
        //输出图象到页面
        File file = new File("./src/main/resources/static/images/aaa.jpg");
        file.delete();
        try {
            ImageIO.write(image, "JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/checkPhone")
    @ResponseBody
    public Map<String, Object> checkPhone(Model model, @RequestParam(value = "phone", required = false) String phone) {
        log.debug("注册-判断手机号" + phone + "是否可用");
        Map map = new HashMap<String, Object>();
        User user = userService.findByPhone(phone);
        if (user == null) {
            //未注册
            map.put("message", "success");
        } else {
            //已注册
            map.put("message", "fail");
        }

        return map;
    }
    
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> checkCode(Model model, @RequestParam(value = "code", required = false) String code) {
        log.debug("注册-判断验证码" + code + "是否可用");
        Map map = new HashMap<String, Object>();
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String vcode = (String) attrs.getRequest().getSession().getAttribute(CodeCaptchaServlet.VERCODE_KEY);

         if (code.equals(vcode)) {
            //验证码正确
            map.put("message", "success");
        } else {
            //验证码错误
            map.put("message", "fail");
        }

        return map;
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public Map<String, Object> checkEmail(Model model, @RequestParam(value = "email", required = false) String email) {
        log.debug("注册-判断邮箱" + email + "是否可用");
        Map map = new HashMap<String, Object>();
        User user = userService.findByEmail(email);
        if (user == null) {
            //未注册
            map.put("message", "success");
        } else {
            //已注册
            map.put("message", "fail");
        }

        return map;
    }
    @RequestMapping("/doRegister")
    public String doRegister(Model model, @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "nickName", required = false) String nickname,
                             @RequestParam(value = "code", required = false) String code) {

        log.debug("注册...");
        if (StringUtils.isBlank(code)) {
            model.addAttribute("error", "非法注册，请重新注册！");
            return RegisterCode.REGIST_ILLGEL.getCode();
        }

        int b = checkValidateCode(code);
        if (b == -1) {
            model.addAttribute("error", "验证码超时，请重新注册！");
            return RegisterCode.REGISTE_TIMEOUT.getCode();
        } else if (b == 0) {
            model.addAttribute("error", "验证码不正确,请重新输入!");
            return RegisterCode.REGIST_ERROR.getCode();
        }


        User user = userService.findByEmail(email);
        if (user != null) {
            model.addAttribute("error", "该用户已经被注册！");
            return RegisterCode.REGISTED.getCode();
        } else {
            user = new User();
            user.setNickName(nickname);

            user.setPassword(MD5Util.encodeToHex("salt"+password));
            user.setPhone(phone);
            user.setEmail(email);
            user.setState("0");
            user.setEnable("0");
            user.setImgUrl("/images/icon_m.jpg");
            //邮件激活码
            String validateCode = MD5Util.encodeToHex("salt"+email + password);
            redisTemplate.opsForValue().set(email, validateCode, 24, TimeUnit.HOURS);// 24小时 有效激活 redis保存激活码

            userService.regist(user);

            log.info("注册成功");
            SendEmail.sendEmailMessage(email, validateCode);
            String message = email + "," + validateCode;
            model.addAttribute("message", message);
            return RegisterCode.REGIST_SUCCESS.getCode();

        }
    }
        // 匹对验证码的正确性

    public int checkValidateCode(String code) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object vercode = attrs.getRequest().getSession().getAttribute("VERCODE_KEY");
        if (null == vercode) {
            return -1;
        }
        if (!code.equalsIgnoreCase(vercode.toString())) {
            return 0;
        }
        return 1;
    }
    @RequestMapping("/activecode")
    public String active(Model model) {
        log.info( "==============激活验证==================" );
        //判断   激活有无过期 是否正确
        //validateCode=
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String validateCode = attrs.getRequest().getParameter( "validateCode" );
        String email = attrs.getRequest().getParameter( "email" );
        String code = redisTemplate.opsForValue().get( email );
        log.info( "验证邮箱为："+email+",邮箱激活码为："+code+",用户链接的激活码为："+validateCode );
        //判断是否已激活

        User userTrue = userService.findByEmail( email );
        if(userTrue!=null && "1".equals( userTrue.getState() )){
            //已激活
            model.addAttribute( "success","您已激活,请直接登录！" );
            return "../login";
        }

        if(code==null){
            //激活码过期
            model.addAttribute( "fail","您的激活码已过期,请重新注册！" );
            userService.deleteByEmail( email );
            return "/regist/activeFail";
        }

        if(StringUtils.isNotBlank( validateCode ) && validateCode.equals( code )){
            //激活码正确
            userTrue.setEnable( "1" );
            userTrue.setState( "1" );
            userService.update( userTrue );
            model.addAttribute( "email",email );
            return "/regist/activeSuccess";
        }else {
            //激活码错误
            model.addAttribute( "fail","您的激活码错误,请重新激活！" );
            return "/regist/activeFail";
        }

    }



    @RequestMapping("/sendEmail")
    @ResponseBody
    public  Map<String,Object> sendEmail(Model model) {
        Map map = new HashMap<String,Object>(  );
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String validateCode = attrs.getRequest().getParameter( "validateCode" );
        String email = attrs.getRequest().getParameter( "email" );
        SendEmail.sendEmailMessage(email,validateCode);
        map.put( "success","success" );
        return map;
    }

//    @RequestMapping("/register")
//    public String register(Model model) {
//
//        log.info("进入注册页面");
//
//        return "../register";
//    }
}

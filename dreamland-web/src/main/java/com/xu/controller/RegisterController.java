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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xu.common.CodeCaptchaServlet;
import com.xu.entity.User;
import com.xu.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	private final static Logger log = Logger.getLogger(RegisterController.class);
    public static final String VERCODE_KEY = "VERCODE_KEY";
    @Autowired
    private UserService userService;
    
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
}

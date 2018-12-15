/*
 * Copyright (C) 2011-2013 dshine.com
 * All rights reserved.
 * ShangHai Dshine - http://www.dshine.com
 */
package com.xu.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wly on 2018/4/17.
 */
@Controller
@RequestMapping(value="/login")
public class CodeCaptchaServlet{

    public static final String VERCODE_KEY = "VERCODE_KEY";
    @RequestMapping(value = "/codeCaptcha", method = RequestMethod.POST)
    protected String codeCaptcha(){
        String url = "aaa";
        return url;
        // request.getSession().removeAttribute(LOGIN_VERCODE_KEY);
        //        request.getSession().removeAttribute(VERCODE_KEY);
        //        // 首先设置页面不缓存
        //        response.setHeader("Pragma", "No-cache");
        //        response.setHeader("Cache-Control", "no-cache");
        //        response.setDateHeader("Expires", 0);
        //
        //        //在内存中创建图象
        //        int iWidth = 55, iHeight = 18;
        //        BufferedImage image = new BufferedImage(iWidth, iHeight,
        //                BufferedImage.TYPE_INT_RGB);
        //        //获取图形上下文
        //        Graphics g = image.getGraphics();
        //        //设定背景色
        //        g.setColor(Color.white);
        //        g.fillRect(0, 0, iWidth, iHeight);
        //        //画边框
        //        g.setColor(Color.black);
        //        g.drawRect(0, 0, iWidth - 1, iHeight - 1);
        //        //取随机产生的认证码(4位数字)
        //        //String rand = Utils.getCharacterAndNumber(4);
        //        int intCount=0; 
        //        intCount=(new Random()).nextInt(9999);// 
        //
        //        if(intCount <1000)intCount+=1000; 
        //        String rand=intCount+"";
        //        //将认证码显示到图象中
        //        g.setColor(Color.black);
        //        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        //        g.drawString(rand, 5, 15);
        //        //随机产生88个干扰点,使图象中的认证码不易被其它程序探测到
        //        Random random = new Random();
        //        for (int iIndex = 0; iIndex < 100; iIndex++) {
        //            int x = random.nextInt(iWidth);
        //            int y = random.nextInt(iHeight);
        //            g.drawLine(x, y, x, y);
        //        }
        //
        //        // 将生成的随机字符串写入session
        //        // request.getSession().setAttribute(LOGIN_VERCODE_KEY, rand);
        //        request.getSession().setAttribute(VERCODE_KEY, rand);
        //        //图象生效
        //        g.dispose();
        //        //输出图象到页面
        //        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    public void init()
            throws ServletException {
    }

    private static final long serialVersionUID = 5413310437308046316L;

}

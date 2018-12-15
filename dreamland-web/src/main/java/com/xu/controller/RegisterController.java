package com.xu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    public static final String VERCODE_KEY = "VERCODE_KEY";
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
        //        return "bbb";
    }
}

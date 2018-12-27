package com.xu;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

import tk.mybatis.spring.annotation.MapperScan;

@Configurable
@EnableTransactionManagement
@SpringBootApplication
//正确为引入tk开头的包而不是org
@MapperScan(basePackages = "com.xu.dao")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
    
    
}

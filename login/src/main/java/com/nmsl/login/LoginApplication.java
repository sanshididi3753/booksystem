package com.nmsl.login;

import com.nmsl.feign.client.MailClient;
import com.nmsl.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.nmsl.login.mapper")
@SpringBootApplication
@EnableFeignClients(clients = MailClient.class,defaultConfiguration = DefaultFeignConfiguration.class)
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class,args);
    }
}

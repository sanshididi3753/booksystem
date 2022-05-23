package com.nmsl.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String mailUsername;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送激活账号邮件
     * @param activationUrl 激活url链接
     * @param email 收件人的邮箱
     */
    public void sendMailForActivationAccount(String activationUrl,String email){
        //创建邮箱对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            //设置邮件主题
            mimeMessageHelper.setSubject("师姐激活,尽在此邮件");
            //设置邮件发送者
            mimeMessageHelper.setFrom(mailUsername);
            //设置邮件接收者 可以多个
            mimeMessageHelper.setTo(email);
            //设置邮件抄送人 可以多个
//            mimeMessageHelper.setCc();
            //设置隐秘抄送人 可以多个
//            mimeMessageHelper.setBcc();
            //设置邮件发送日期
            mimeMessageHelper.setSentDate(new Date());
            //创建上下文环境
            Context context = new Context();
            activationUrl = "http://localhost:8081/login/user/activation?confirmCode="+activationUrl;
            context.setVariable("activationUrl",activationUrl);
            String text = templateEngine.process("activationAccount",context);
            //设置邮件的正文
            mimeMessageHelper.setText(text,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //邮件发送
        javaMailSender.send(mimeMessage);
    }
}

package com.nmsl.mail.controller;


import com.nmsl.feign.pojo.Mail;
import com.nmsl.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public void sendMail(@RequestBody Mail mail){
        System.out.println(mail.getConfirmCode()+"+"+mail.getEmail());
//        mailService.sendMailForActivationAccount(mail.getConfirmCode(),mail.getEmail());
    }
}

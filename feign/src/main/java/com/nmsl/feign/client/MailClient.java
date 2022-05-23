package com.nmsl.feign.client;

import com.nmsl.feign.pojo.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "mail")
public interface MailClient {

    @PostMapping("/mail/send")
    void sendMail(Mail mail);

}

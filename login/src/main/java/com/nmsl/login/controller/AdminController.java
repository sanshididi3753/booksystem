package com.nmsl.login.controller;


import com.nmsl.login.pojo.Admin;
import com.nmsl.login.pojo.ResultInfo;
import com.nmsl.login.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 账号登录
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public ResultInfo loginAccount(@Validated @RequestBody Admin admin){
        return adminService.loginAccount(admin);
    }

}

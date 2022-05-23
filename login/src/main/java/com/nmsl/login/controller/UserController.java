package com.nmsl.login.controller;

import com.nmsl.login.pojo.ResultInfo;
import com.nmsl.login.pojo.User;
import com.nmsl.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册账号
     * @param
     * @return
     */
    @PostMapping("/create")
    public ResultInfo createAccount(@Validated @RequestBody User user){
        return userService.createAccount(user);
    }

    /**
     * 账号登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultInfo loginAccount(@Validated @RequestBody User user){
        return userService.loginAccount(user);
    }

    /**
     * 账号激活
     * @param confirmCode
     * @return
     */
    @GetMapping("activation")
    public ResultInfo activationAccount(String confirmCode){
        return userService.activationAccount(confirmCode);
    }
}

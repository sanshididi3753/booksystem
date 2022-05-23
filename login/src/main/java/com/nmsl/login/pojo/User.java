package com.nmsl.login.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId; //主键
    private String userPwd;//密码
    private String confirmCode;//确认码
    private String userEmail;//邮箱
    private LocalDateTime activationTime;//激活失效时间
    private Byte isValid;//是否可用

}

package com.nmsl.login.service;

import com.nmsl.login.mapper.AdminMapper;
import com.nmsl.login.pojo.Admin;
import com.nmsl.login.pojo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public ResultInfo loginAccount(Admin admin) {
        ResultInfo resultInfo = new ResultInfo();
        List<Admin> result = adminMapper.selectAdminByName(admin.getAdminName());
        if (result.size()==0){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("未查询到该用户");
            return resultInfo;
        }else if (result.size()>1){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账户异常，请联系系统管理员");
            return resultInfo;
        }else {
            if (result.get(0).getAdminPwd().equals(admin.getAdminPwd())){
                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("登录成功");
            }else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名或密码输入错误");
            }
            return resultInfo;
        }
    }
}

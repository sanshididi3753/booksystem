package com.nmsl.login.service;

import cn.hutool.core.util.IdUtil;
import com.nmsl.feign.client.MailClient;
import com.nmsl.feign.pojo.Mail;
import com.nmsl.login.mapper.UserMapper;
import com.nmsl.login.pojo.ResultInfo;
import com.nmsl.login.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;

    /**
     * 账号注册
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)//出现异常进行回滚
    public ResultInfo createAccount(User user){
        ResultInfo resultInfo = new ResultInfo();
        // 判断是否注册过
        List<User> userList = userMapper.selectUserByEmailWithValid(user);
        if (userList.size()==1){
            if (userList.get(0).getIsValid()==1) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("此账号已注册,请前往登录");
            }else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("此账号已注册,请前往邮箱激活");
            }
            return resultInfo;
        }
        //雪花算法生成确认码
        String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();
        //盐（加密算法）
//        String salt = RandomUtil.randomString(6);
        //加密密码：原始密码+盐
//        String md5Pwd = SecureUtil.md5(com.nmsl.user.getUser_pwd()+salt);
        //激活失效时间：1小时
        LocalDateTime ldt = LocalDateTime.now().plusHours(1);
        //初始化账号信息
//        user.setSalt(salt);
//        user.setUser_pwd(md5Pwd);
        user.setConfirmCode(confirmCode);
        user.setActivationTime(ldt);
        user.setIsValid((byte)0);
        //新增账号
        int result = userMapper.insertUser(user);

        if(result>0){
            //发送邮件
            Mail mail = new Mail();
            mail.setEmail(user.getUserEmail());
            mail.setConfirmCode(confirmCode);
            mailClient.sendMail(mail);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注册成功,请前往邮箱进行账号激活");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }
        return resultInfo;
    }

    /**
     * 账号登录
     * @param user
     * @return
     */
    public ResultInfo loginAccount(User user){
        ResultInfo resultInfo = new ResultInfo();
        //根据邮箱查询用户
        List<User> userList = userMapper.selectUserByEmail(user);
        //查询不到结果
        if (userList == null || userList.isEmpty()){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账户不存在或未激活");
            return resultInfo;
        }
        //查询到多个用户
        if (userList.size()>1){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账户异常，请联系管理员");
            return resultInfo;
        }
        //查询到一个用户
        //比较密码
        if (userList.get(0).getUserPwd().equals(user.getUserPwd())) {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("登录成功");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }
        return resultInfo;
    }

    /**
     * 账号激活
     * @param confirmCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)//出现异常进行回滚
    public ResultInfo activationAccount(String confirmCode) {
        ResultInfo resultInfo = new ResultInfo();
        //根据确认码查询用户
        User user = userMapper.selectUserByConfirmCode(confirmCode);
        //判断激活时间是否超时
        boolean after = LocalDateTime.now().isAfter(user.getActivationTime());
        if (after){
            userMapper.deleteByConfirmCode(confirmCode);
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("连接已失效，请重新注册");
            return resultInfo;
        }
        //根据确认码修改状态值为1
        int result = userMapper.updateUserByConfirmCode(confirmCode);
        if (result>0){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("激活成功");
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("激活失败");
        }
        return resultInfo;
    }
}

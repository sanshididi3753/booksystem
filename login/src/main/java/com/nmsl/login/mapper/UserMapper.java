package com.nmsl.login.mapper;

import com.nmsl.login.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Insert("INSERT INTO com.nmsl.user(user_pwd,user_email,confirmCode,activationTime,is_valid) " +
            "VALUES(#{userPwd},#{userEmail},#{confirmCode},#{activationTime},#{isValid})")
    int insertUser(User user);

    /**
     * 根据确认码查询用户
     * @param confirmCode
     * @return
     */
    @Select("SELECT user_email,activationTime FROM com.nmsl.user WHERE confirmCode=#{confirmCode} AND is_valid=0 ")
    User selectUserByConfirmCode(@Param("confirmCode") String confirmCode);

    /**
     * 根据确认码查询用户并修改状态值为1（可用）
     * @param confirmCode
     * @return
     */
    @Update("UPDATE com.nmsl.user SET is_valid = 1 WHERE confirmCode=#{confirmCode}")
    int updateUserByConfirmCode(@Param("confirmCode") String confirmCode);

    @Select("SELECT user_pwd, user_email FROM com.nmsl.user WHERE user_email = #{userEmail} AND is_valid=1")
    List<User> selectUserByEmail(User user);

    @Select("SELECT is_valid FROM com.nmsl.user WHERE user_email = #{userEmail}")
    List<User> selectUserByEmailWithValid(User user);

    @Delete("DELETE FROM com.nmsl.user WHERE confirmCode=#{confirmCode}")
    void deleteByConfirmCode(@Param("confirmCode") String confirmCode);
}

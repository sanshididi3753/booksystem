package com.nmsl.login.mapper;

import com.nmsl.login.pojo.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("SELECT * FROM admin WHERE admin_name = #{adminName}")
    List<Admin> selectAdminByName(String adminName);
}

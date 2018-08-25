package com.zhima.webauth.mapper;

import com.zhima.webauth.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}
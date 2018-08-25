package com.zhima.webauth.mapper;

import com.zhima.webauth.po.UserMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMenu record);

    UserMenu selectByPrimaryKey(Integer id);

    List<UserMenu> selectAll();

    int updateByPrimaryKey(UserMenu record);
}
package com.zhima.webauth.mapper;

import com.zhima.webauth.po.UserButton;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserButtonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserButton record);

    UserButton selectByPrimaryKey(Integer id);

    List<UserButton> selectAll();

    int updateByPrimaryKey(UserButton record);
}
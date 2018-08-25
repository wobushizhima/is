package com.zhima.webauth.mapper;

import com.zhima.webauth.po.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    List<RoleMenu> selectAll();

    int updateByPrimaryKey(RoleMenu record);
}
package com.zhima.webauth.mapper;

import com.zhima.webauth.po.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}
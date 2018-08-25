package com.zhima.webauth.mapper;

import com.zhima.webauth.po.RoleButton;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleButtonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleButton record);

    RoleButton selectByPrimaryKey(Integer id);

    List<RoleButton> selectAll();

    int updateByPrimaryKey(RoleButton record);
}
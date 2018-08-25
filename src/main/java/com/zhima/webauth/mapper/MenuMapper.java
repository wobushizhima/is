package com.zhima.webauth.mapper;

import com.zhima.webauth.po.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
}
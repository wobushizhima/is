package com.zhima.webauth.mapper;

import com.zhima.webauth.po.Button;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ButtonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Button record);

    Button selectByPrimaryKey(Integer id);

    List<Button> selectAll();

    int updateByPrimaryKey(Button record);
}
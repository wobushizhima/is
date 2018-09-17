package com.zhima.sxdx.mapper;

import com.github.pagehelper.Page;
import com.zhima.sxdx.po.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* 描述：成员属性 mapper层
* @author superz
* @date 2018/08/27
*/
@Mapper
public interface ContentMapper {

     Content getById(Integer id);

     int insert(Content content);

     void update(Content content);

     Page<Content> list(Map<String, Object> param);

     void delete(Integer id);
}
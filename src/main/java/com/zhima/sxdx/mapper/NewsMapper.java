package com.zhima.sxdx.mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhima.sxdx.po.News;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* 描述：成员属性 mapper层
* @author superz
* @date 2018/08/27
*/
@Mapper
public interface NewsMapper {

     News getById(Integer id);

     int insert(News news);

     void update(News news);

     Page<News> list(Map<String, Object> param);

     void deleteById(Integer id);
}
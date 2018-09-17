package com.zhima.sxdx.service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhima.sxdx.po.News;

import java.util.Map;

/**
* 描述：成员属性 服务实现层接口
* @author superz
* @date 2018/08/27
*/
public interface INewsService{

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    News getById(Integer id)throws Exception;

    News saveNews(News news) throws Exception;

    void deleteNews(Integer id) throws Exception;

    void updateNews(News news) throws Exception;

    Page<News> list(Map<String, Object> param);

}
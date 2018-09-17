package com.zhima.sxdx.service;

import com.github.pagehelper.Page;
import com.zhima.sxdx.po.Content;

import java.util.Map;

/**
* 描述：成员属性 服务实现层接口
* @author superz
* @date 2018/08/27
*/
public interface IContentService{

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    Content getById(Integer id)throws Exception;

    Content saveContent(Content content) throws Exception;

    void deleteContent(Integer id) throws Exception;

    void updateContent(Content content) throws Exception;

    Page<Content> list(Map<String, Object> param) throws Exception;

}
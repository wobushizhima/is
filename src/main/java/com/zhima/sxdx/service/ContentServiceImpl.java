package com.zhima.sxdx.service;
import com.zhima.sxdx.mapper.ContentMapper;
import com.zhima.sxdx.po.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.Date;
import java.util.Map;

/**
* 描述：成员属性 服务实现层
* @author superz
* @date 2018/08/27
*/
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentMapper contentMapper;


    @Override
    public Content getById(Integer id) throws Exception {
        Content content = contentMapper.getById(id);
        return content;
    }

    @Override
    public Content saveContent(Content content) throws Exception {
        if(content.getCreateTime()==null){
            content.setCreateTime(new Date());
        }
        contentMapper.insert(content);
        return content;
    }

    @Override
    public void updateContent(Content content)throws Exception {
        contentMapper.update(content);
    }

    @Override
    public void deleteContent(Integer id) throws Exception{
        contentMapper.delete(id);
    }

    @Override
    public Page<Content> list(Map<String, Object> param) throws Exception{
        int pageNo=param.get("pageNo")==null?1:Integer.valueOf(param.get("pageNo")+"");
        int pageSize=param.get("pageSize")==null?1:Integer.valueOf(param.get("pageSize")+"");
        PageHelper.startPage(pageNo,pageSize);
        return contentMapper.list(param);
    }
}
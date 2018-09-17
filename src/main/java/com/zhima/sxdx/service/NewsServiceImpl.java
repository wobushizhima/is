package com.zhima.sxdx.service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhima.sxdx.mapper.NewsMapper;
import com.zhima.sxdx.po.News;
import com.zhima.sxdx.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* 描述：成员属性 服务实现层
* @author superz
* @date 2018/08/27
*/
@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public News getById(Integer id) throws Exception {
        News news = newsMapper.getById(id);
        return news;
    }

    @Override
    public News saveNews(News news) throws Exception {
        newsMapper.insert(news);
        return news;
    }

    @Override
    public void updateNews(News news)throws Exception {
        newsMapper.update(news);
    }

    @Override
    public Page<News> list(Map<String, Object> param) {
        int pageNo=param.get("pageNo")==null?1:Integer.valueOf(param.get("pageNo")+"");
        int pageSize=param.get("pageSize")==null?10:Integer.valueOf(param.get("pageSize")+"");
        PageHelper.startPage(pageNo,pageSize);
        Page<News> list=newsMapper.list(param);
        return list;
    }

    @Override
    public void deleteNews(Integer id) throws Exception{
        newsMapper.deleteById(id);
    }
}
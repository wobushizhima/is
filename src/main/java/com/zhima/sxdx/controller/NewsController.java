package com.zhima.sxdx.controller;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhima.sxdx.service.INewsService;
import com.zhima.sxdx.po.News;
import com.zhima.dto.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
* 描述：成员属性控制层
* @author superz
* @date 2018/08/27
*/
@RestController
@RequestMapping(value = "/news")
@CrossOrigin
public class NewsController {

    @Autowired
    private INewsService newsService;

    @PostMapping(value = "/list")
    public JsonResult list(@RequestBody Map params)throws Exception{
        JsonResult jsonResult=new JsonResult();
        Page<News> news = newsService.list(params);
        PageInfo pageInfo=new PageInfo(news);
        jsonResult.setResult(pageInfo);
        return jsonResult;

    }

    /**
    * 描述：根据Id 查询
    * @param id  成员属性id
    */
    @PostMapping(value = "/findById")
    public JsonResult findById(@RequestParam(value="id") String id)throws Exception {
        JsonResult jsonResult=new JsonResult();
        News news = newsService.getById(Integer.valueOf(id));
        jsonResult.setResult(news);
        return jsonResult;
    }

    /**
    * 描述:创建成员属性
    * @param news  成员属性
    */
    @PostMapping(value = "/save")
    public JsonResult save(@RequestBody News news) throws Exception {
        JsonResult jsonResult=new JsonResult();
        news=newsService.saveNews(news);
        return jsonResult;
    }

    /**
    * 描述：删除成员属性
    * @param id 成员属性id
    */
    @PostMapping(value = "/delete")
    public JsonResult deleteById(@RequestParam(value="id") String id) throws Exception {
        JsonResult jsonResult=new JsonResult();
        newsService.deleteNews(Integer.valueOf(id));
        return jsonResult;
    }

    /**
    * 描述：更新成员属性
    * @param  news 成员属性
    */
    @PostMapping(value = "/update")
    public JsonResult updateNews(@RequestBody News news) throws Exception {
        JsonResult jsonResult=new JsonResult();
        newsService.updateNews(news);
        return jsonResult;
    }

}
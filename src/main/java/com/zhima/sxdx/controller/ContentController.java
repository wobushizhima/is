package com.zhima.sxdx.controller;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhima.dto.JsonResult;
import com.zhima.sxdx.po.Content;
import com.zhima.sxdx.service.IContentService;
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
@RequestMapping(value = "/content", produces = "application/json;charset=UTF-8")
public class ContentController {

    @Autowired
    private IContentService contentService;

    /**
    * 描述：根据Id 查询
    * @param id  成员属性
    */
    @PostMapping(value = "/findById")
    public JsonResult findById(@RequestParam(value="id") String id)throws Exception {
        JsonResult jsonResult=new JsonResult();
        Content content = contentService.getById(Integer.valueOf(id));
        jsonResult.setResult(content);
        return jsonResult;
    }

    /**
    * 描述:创建成员属性
    * @param  content 成员属性
*/
    @PostMapping(value = "/save")
    public JsonResult save(@RequestBody Content content) throws Exception {
        JsonResult jsonResult=new JsonResult();
        content=contentService.saveContent(content);
        return jsonResult;
    }

    /**
    * 描述：删除成员属性
    * @param id 成员属性
    */
    @PostMapping(value = "/delete")
    public JsonResult deleteById(@RequestParam(value="id") String id) throws Exception {
        JsonResult jsonResult=new JsonResult();
        contentService.deleteContent(Integer.valueOf(id));
        return jsonResult;
    }

    /**
    * 描述：更新成员属性
    * @param  content  成员属性
    */
    @PostMapping(value = "/update")
    public JsonResult updateContent(@RequestBody Content content) throws Exception {
        JsonResult jsonResult=new JsonResult();
        contentService.updateContent(content);
        return jsonResult;
    }

    /**
    * 描述：根据params 查询
    * @param params  成员属性
    */
    @PostMapping(value = "/list")
    public JsonResult list(@RequestBody Map params)throws Exception {
        JsonResult jsonResult=new JsonResult();
        Page<Content> page=contentService.list(params);
        PageInfo pageInfo=new PageInfo(page);
        jsonResult.setResult(pageInfo);
        return jsonResult;
    }

    /**
     * 描述：根据params 查询
     */
    @PostMapping(value = "/last")
    public JsonResult last()throws Exception {
        JsonResult jsonResult=new JsonResult();
        Page<Content> page=contentService.list(new HashMap<String,Object>());
        if(page.size()==0){
            jsonResult.setResult(null);
        }else{
            jsonResult.setResult(page.get(0));
        }
        return jsonResult;
    }

}
package com.zhima.sxdx.po;

import java.util.Date;

/**
 * 描述：成员属性模型
 *
 * @author superz
 * @date 2018/08/27
 */
public class News {

    /**
     *
     */

    private Integer id;


    /**
     *
     */


    private Date createTime;

    /**
     *
     */


    /**
     *
     */
    private String title;


    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
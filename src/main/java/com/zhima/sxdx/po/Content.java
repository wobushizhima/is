package com.zhima.sxdx.po;
import java.util.Date;

/**
* 描述：成员属性模型
* @author superz
* @date 2018/08/27
*/
public class Content{

    /**
    *
    */

        private Integer id;



    /**
    *
    */
        private String content;




    /**
    *
    */



        private Date createTime;


        public Integer getId() {
        return this.id;
        }

        public void setId(Integer id) {
        this.id = id;
        }



        public String getContent() {
        return this.content;
        }

        public void setContent(String content) {
        this.content = content;
        }





        public Date getCreateTime() {
        return this.createTime;
        }

        public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        }


}
package com.zhima.ueditor.controller;

import com.zhima.ueditor.ActionEnter;
import com.zhima.ueditor.constant.UeditorConstant;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

/**
 * Created by superz on 2018/9/12.
 */
@RestController
@CrossOrigin
public class UeditorController {

   // @RequestMapping(value = "/exec")
    @RequestMapping(value = "/ueditor/exec")
//    @ResponseBody
    public void exec(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("i'm here");
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        String result=new ActionEnter( request, rootPath ).exec();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter writer=response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
        writer=null;
    }

    //@RequestMapping(value = "/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request, HttpServletResponse response,String action) throws IOException {
        if(action.equals("config")){
           StringBuffer buffer = new StringBuffer();
            BufferedReader bf= new BufferedReader(new FileReader("D:\\config.json"));
            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                buffer.append(s.trim());
            }

            String xml = buffer.toString();

            String s2= "{\n"+
                    "            \"imageActionName\": \"uploadimage\",\n" +
                    "                \"imageFieldName\": \"upfile\", \n" +
                    "                \"imageMaxSize\": 2048000, \n" +
                    "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                    "                \"imageCompressEnable\": true, \n" +
                    "                \"imageCompressBorder\": 1600, \n" +
                    "                \"imageInsertAlign\": \"none\", \n" +
                    "                \"imageUrlPrefix\":\"http://127.0.0.1/\",\n" +
                    "                \"imagePathFormat\": \"upload/{filename}\" }";
            System.out.println(xml);
            return s2;

        }
        return action;



    }

    @RequestMapping(value = "/imgUpdate")
    @ResponseBody
    public String imgUpdate(@RequestParam("upfile") MultipartFile file) {
        System.out.println("upload");
        if (file.isEmpty()) {
            return "error";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 这里我使用随机字符串来重新命名图片
        fileName = Calendar.getInstance().getTimeInMillis()  + suffixName;
        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        File dest = new File(UeditorConstant.IMAGE_UPDATE_PATH + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + "/upload/"+fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";
            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}

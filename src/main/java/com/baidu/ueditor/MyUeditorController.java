package com.baidu.ueditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyUeditorController {

	@RequestMapping("/")
    public String index(){
		return "ueditor";
	}

	@Autowired
	private ActionEnter actionEnter;

	@ResponseBody
	@RequestMapping("/ueditor/exec")
	public String exe(HttpServletRequest request){
		System.out.println(11111);
		return actionEnter.exec(request);
	}

}
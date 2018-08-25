package com.zhima.webauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by superz on 2018/7/28.
 */
@RestController
public class TestController {

    @GetMapping(value="/a")
    public Object a(){
        return "a";
    }

    @GetMapping(value="/b")
    public Object b(){
        return "b";
    }
}

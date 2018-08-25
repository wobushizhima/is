package com.zhima.webauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by superz on 2018/8/7.
 */
@Component
public class MyAuth {

    @Autowired
    private IUserService userService;

    public void register(){

    }

    public void login(){

    }

    public void logout(){

    }

    public void changePassword(){

    }
}

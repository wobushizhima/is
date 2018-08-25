package com.zhima.webauth;

import com.zhima.webauth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by superz on 2018/3/29.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.zhima.webauth.po.User myUser=userMapper.findByUsername(name);
        if(myUser==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        User user = mockUser(myUser.getPassword(),myUser.getPassword(),null);
            return user;
    }

    private User mockUser(String userName, String password, String userType) {

        Collection<GrantedAuthority> authorities = new HashSet<>();
        //用户所拥有的角色信息
        if("10".equals(userType)){
            authorities.add(new SimpleGrantedAuthority("custom"));//用户所拥有的角色信息
        }else {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }

        User user = new User(userName,password,authorities);
        return user;

    }

}

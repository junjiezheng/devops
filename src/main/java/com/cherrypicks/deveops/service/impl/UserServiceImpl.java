package com.cherrypicks.deveops.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cherrypicks.devops.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	 //正常情况下，当loadUserByUsername传入用户名后，
    //应该连接数据库从数据库中根据用户名把该用户的信息取出来，
    //本例出于简单演示的目的，不再额外的引入数据库，
    //假设已经知道用户名和密码，硬编码写死了用户名和密码
    public static final String USER_NAME = "rei";
    public static final String USER_PASSWORD = "123";
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " 加载信息");
        if (USER_NAME.equals(username)) {
            return new User(USER_NAME, USER_PASSWORD, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("用户不存在:" + username);
        }       
    }

}

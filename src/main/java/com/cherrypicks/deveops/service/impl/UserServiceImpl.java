package com.cherrypicks.deveops.service.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cherrypicks.devops.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!"zhengjunjie".equals(username)){
            throw new UsernameNotFoundException("用户名不存在");
        }
		BCryptPasswordEncoder bcEndcoder=new BCryptPasswordEncoder();
		return new User(username, bcEndcoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CLIENT"));
	}

}

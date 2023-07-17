package com.cherrypicks.devops.test;

import java.util.Date;

import javax.persistence.Column;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cherrypicks.devops.entity.UserInfo;
import com.cherrypicks.devops.repository.UserInfoRepository;
import com.cherrypicks.devops.util.DateUtil;
import com.cherrypicks.devops.util.JsonUtil;

@SpringBootTest
public class junitTest {
	
	@Autowired
	private UserInfoRepository userRepository;
	
	@Test
	@DisplayName("Test user demo.")
	public void testUser() {
		Date date1=DateUtil.parseDateTime("2023-04-20 19:00:00");
	    Date date2=DateUtil.parseDateTime("2023-04-20 19:00:00");
	    UserInfo user1=UserInfo.builder().userId(1L).userName("Rei001").birth(date1).build();
	    UserInfo user2=UserInfo.builder().userId(2L).userName("Rei001").birth(date2).build();
	    String str1=JsonUtil.toJson(user1);
	    System.out.println("=====================================>"+JsonUtil.fromJSON(str1,UserInfo.class).toString());
	    String str2=JsonUtil.toJson(user2);
	    System.out.println("=====================================>"+JsonUtil.fromJSON(str2,UserInfo.class).toString());
	}
	
	@Test
	@DisplayName("Encrypt pwd")
	public void testPwd() {
		String pwd="123456";
		BCryptPasswordEncoder bcryptPasswordEncoder=new BCryptPasswordEncoder();
		String encoder=bcryptPasswordEncoder.encode(pwd);
		boolean ischeck=bcryptPasswordEncoder.matches(pwd, encoder);
		System.out.println("密码是否正确:"+ischeck);
	}
	
	@Test
	@DisplayName("Add user")
	public void testAddUser(){
		UserInfo u=UserInfo.builder().
				userName("Rei01").userPwd("123").userAge(18).gender("M").birth(DateUtil.parseDateTime("1993-04-07 11:52:32")).build();
		u.setCreatedAt(DateUtil.getCurrentDate());
		u.setCreatedBy("System");
		userRepository.save(u);
		System.out.println("New Uid="+u.getUserId());
	}
	
	
	
}

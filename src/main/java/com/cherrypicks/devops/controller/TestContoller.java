package com.cherrypicks.devops.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cherrypicks.devops.vo.ResultVO;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class TestContoller extends BaseController {	
	
	@Value("${env.name}")
	private String env;
	
	@GetMapping(value="/test")
    public ResultVO<Object> test(final HttpServletRequest request,final HttpServletResponse response,
    		@RequestParam(name="tag",required = false) String tag){
		Assert.hasText(tag,"tag parameter invalid.");
		Map<String,Object> map=new HashMap<>();
		map.put("tag", tag);
		map.put("env", env);
		map.put("staffName", "Rei.zheng");
		map.put("operation", "Docker deploy test");
	    map.put("version", "2.0.0");
	    return ResultVO.info(map);
     }
	
	
	
}

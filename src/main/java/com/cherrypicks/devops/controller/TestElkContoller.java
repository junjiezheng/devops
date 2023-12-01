package com.cherrypicks.devops.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cherrypicks.devops.util.JsonUtil;
import com.cherrypicks.devops.vo.ResultVO;

@RestController
public class TestElkContoller extends BaseController {	
	
	private static final Logger log = LoggerFactory.getLogger(TestElkContoller .class);
	
	@GetMapping(value="/testInfo")
    public ResultVO<Object> testInfo(final HttpServletRequest request,final HttpServletResponse response,
    		@RequestParam(name="message",required = true) String msg){
		Map<String,Object> map=new HashMap<>();
		map.put("msg", msg);
		ResultVO<Object> output=ResultVO.info(map);
		log.info("------------------> Api[{}] Param{msg={}} Response:{} <-----------------","/testInfo", msg, JsonUtil.toJson(output));
		return output;
     }
	
	@GetMapping(value="/testError")
    public ResultVO<Object> testError(final HttpServletRequest request,final HttpServletResponse response){
		int i=1/0;
	    return ResultVO.info(i);
     }
	
}

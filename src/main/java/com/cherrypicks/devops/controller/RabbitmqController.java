package com.cherrypicks.devops.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cherrypicks.devops.enums.ExceptionEnum;
import com.cherrypicks.devops.exception.BaseApiException;
import com.cherrypicks.devops.util.DateUtil;
import com.cherrypicks.devops.util.JsonUtil;
import com.cherrypicks.devops.vo.ResultVO;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class RabbitmqController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping("/produceSendCustomQueue")
	public ResultVO<Object> produceSendCustomQueue(final HttpServletRequest request,final HttpServletResponse response,
			@RequestParam(value = "msg",required =false) String msg) {
		
		if(StringUtils.isBlank(msg)) {
			throw new BaseApiException(ExceptionEnum.IS_NOT_NULL.getCode(),ExceptionEnum.IS_NOT_NULL.getMsg(),"msg");
		}
		
		MessageProperties msgProperties=new MessageProperties();
		msgProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		msgProperties.setContentType("UTF-8");
		
		Map<String,Object> msgMap=new HashMap<>();
		msgMap.put("messageId ", String.valueOf(UUID.randomUUID()));
		msgMap.put("messgeData", msg);
		msgMap.put("cresteTime", DateUtil.formatTime(DateUtil.getCurrentDate()));
		String msgStr=JsonUtil.toJson(msgMap);		
		
		Message message=new Message(msgStr.getBytes(), msgProperties);		
		rabbitTemplate.convertAndSend("custom-exchange","topic.custom.first" , message);
		return ResultVO.info("success");
	}

}

package com.cherrypicks.devops.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cherrypicks.devops.enums.ExceptionEnum;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class ErrorController extends BasicErrorController{

	public ErrorController() {
		super(new DefaultErrorAttributes(), new ErrorProperties());
	}
	
	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections
				.unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}
	
	@Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<>();
        map.put("result", "");
        map.put("errorCode",ExceptionEnum.INTERNAL_SERVER_ERROR.getCode());
        map.put("errorMessage", ExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
        return new ResponseEntity<>(map, HttpStatus.valueOf(200));
    }	
	
}

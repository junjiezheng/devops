package com.cherrypicks.devops.config;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cherrypicks.devops.enums.ExceptionEnum;
import com.cherrypicks.devops.exception.BaseApiException;
import com.cherrypicks.devops.vo.ResultVO;

/**
 * RestControllerAdvice，统一异常处理
 */
@RestControllerAdvice
public class ExceptionHandlerConfig {
	
	 /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BaseApiException.class)
    @ResponseBody
    public ResultVO<String> exceptionHandler(BaseApiException e) {
        return ResultVO.error(e.getCode(), e.getMsg());
    }
    
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultVO<String> exceptionHandler(MissingServletRequestParameterException e) {
    	return ResultVO.error(ExceptionEnum.BAD_REQUEST.getCode(),ExceptionEnum.BAD_REQUEST.getMsg());
    }
    
}

package com.cherrypicks.devops.vo;

import com.cherrypicks.devops.enums.ExceptionEnum;

public class ResultVO<T> {
	
    private T result;
    private String errorCode = ExceptionEnum.SUCCESS.getCode();
    private String errorMessage = "";

    public static ResultVO<Object> info(Object result) {
       return new ResultVO<>(result);
    }
    
    public static ResultVO<String> error(String errorCode,String errorMessage) {
        return new ResultVO<>(errorCode,errorMessage);
     } 
    
    public ResultVO(T result){
    	this.result=result;
    }
    
    public ResultVO(String errorCode,String errorMessage){
    	this.errorCode=errorCode;
    	this.errorMessage=errorMessage;
    }
    
    public T getResult() {
        return result;
    }
    
    public void setResult(final T result) {
        this.result = result;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    
}

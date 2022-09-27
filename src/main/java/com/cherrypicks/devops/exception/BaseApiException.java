package com.cherrypicks.devops.exception;

import com.cherrypicks.devops.enums.ExceptionEnum;

public class BaseApiException  extends RuntimeException{

	private static final long serialVersionUID = -88420440662949193L;
	
	private static final String ERROR_CODE="errorCode";
	private static final String ERROR_MSG="errorMsg";
	
	private final String code;
	private final String msg;
	
	public BaseApiException() {
		super();
		this.code=ExceptionEnum.UNKNOWN.getCode();
		this.msg=ExceptionEnum.UNKNOWN.getMsg();
	}
	
	public BaseApiException(ExceptionEnum exceptionEnum) {
        super("{"+ERROR_CODE+":" + exceptionEnum.getCode() + ","+ERROR_MSG+":" + exceptionEnum.getMsg() + "}");
        this.code = exceptionEnum.getCode();
        this.msg=exceptionEnum.getMsg();
    }

    public BaseApiException(String code, String msg) {
        super("{"+ERROR_CODE+":" + code + ","+ERROR_MSG+":" + msg + "}");
        this.code = code;
        this.msg=msg;
    }

    public BaseApiException(String code, String msg, Object... args) {
        super("{"+ERROR_CODE+":" + code + ","+ERROR_MSG+":" + String.format(msg, args) + "}");
        this.code = code;
        this.msg = String.format(msg, args);
    }
    
    public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}    
}

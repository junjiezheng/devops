package com.cherrypicks.devops.enums;

public enum ExceptionEnum {
	
	SUCCESS("0","SUCCESS"),
	// 400
    BAD_REQUEST("400", "请求参数格式不正确!"),
    UNAUTHORIZED("401", "登录凭证过期!"),
    FORBIDDEN("403", "没有访问权限!"),
    NOT_FOUND("404", "请求的资源找不到!"),
    // 500
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVICE_UNAVAILABLE("503", "服务器正忙，请稍后再试!"),
    // 未知异常
    UNKNOWN("10000", "未知异常!"),
    // 自定义
    IS_NOT_NULL("10001","参数不能为空");
	
	private String code;
	private String msg;
	
	ExceptionEnum(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
}

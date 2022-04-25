package com.cherrypicks.devops.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable{
	
	private static final long serialVersionUID = 8691068281487714541L;
	private T result;
	private String statusCode = "0";
	private String statusMessage = "";
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}

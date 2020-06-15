package com.bazinga.au.exception;

public class ErrorItem {
private String code;
private String message;
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "ErrorItem [code=" + code + ", message=" + message + "]";
}
}

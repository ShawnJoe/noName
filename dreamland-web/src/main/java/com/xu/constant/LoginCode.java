package com.xu.constant;

public enum LoginCode {
	LOGIN_SUCESS("0","登陆成功"),LOGIN_CODENULL("1","验证码为空"), LOGIN_CODEERROR("2","验证码错误"),LOGIN_STATE("3","未激活"),LOGIN_ERROR("4","登录失败");
	private String code;
	private String value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private LoginCode(String code,String value) {
		this.code = code;
		this.value = value;
	}
	
}

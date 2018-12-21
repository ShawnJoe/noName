package com.xu.constant;

public enum RegisterCode {
	REGIST_SUCCESS("0","成功"), REGISTE_TIMEOUT("1","验证码超时"),REGIST_ERROR("2","验证码错误"),REGISTED("4","已注册"),REGIST_ILLGEL("5","非法注册"),INNER_ERROR("9","未知错误");
	private String code;
	private String value;
	RegisterCode(String code,String value) {
		this.code = code;
		this.value = value;
	}
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
}

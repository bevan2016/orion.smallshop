package com.orion.smallshop.errors;

public enum ErrorCode {
	PRODUCT_INSUFFICIENT("E0001");

	private String code;

	ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}

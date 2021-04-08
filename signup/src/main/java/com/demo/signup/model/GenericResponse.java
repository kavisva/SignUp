package com.demo.signup.model;

import org.springframework.stereotype.Component;

@Component
public class GenericResponse {
    public GenericResponse() {
	    super();
	}
	
	private String code;
	private String message;
	private Object data;

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

	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public GenericResponse(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	

}

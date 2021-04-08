package com.demo.signup.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date createdDate;
	private String message;
	private String details;
	private String code;
	
	public ErrorDetails(String code, String message,String details) {
		this.createdDate = new Date();
		this.message = message;
		this.details = details;
		this.code = code;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
	
}

package com.demo.signup.exception;

@SuppressWarnings("serial")
public class DemoSignupException extends RuntimeException{
	String message;

	public DemoSignupException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemoSignupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DemoSignupException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DemoSignupException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DemoSignupException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}

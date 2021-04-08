package com.demo.signup.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.signup.utility.Constants;



@ControllerAdvice

// This is a CustomException Handler where all any exception is handled here
// Also sending Error Event data to telemetry service in case of any exception.
// Custom Exception here uses class ErrorDetails class .

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(Constants.FAILURE_CODE, ex.getMessage(),
				request.getDescription(false));
	    logger.error("StackTrace handleEntityNotFound: " , this.getStackTrace(ex));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error("StackTrace handleAllExceptions: " , this.getStackTrace(ex));
		ErrorDetails errorDetails = new ErrorDetails(Constants.FAILURE_CODE,ex.getMessage(),
				request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method used to fetch the Stack trace of the Exception object.
	 * 
	 * @param ex - Exception object
	 * @return String - This returns Stack trace of the Exception.
	 */
	private String getStackTrace(Exception ex) {
		if (ex == null) {
			throw new IllegalArgumentException("Exception == null");
		}
		StringWriter stringWriter = new StringWriter();
		try {
			PrintWriter printWriter = new PrintWriter(stringWriter);
			try {
				ex.printStackTrace(printWriter);
				return stringWriter.toString();
			} finally {
				printWriter.close();
			}
		} finally {
			try {
				stringWriter.close();
			} catch (Exception e) {

			}

		}
	}

}

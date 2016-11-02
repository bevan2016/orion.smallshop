package com.orion.smallshop.errors;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	// The HTTP status code (e.g., 404)
	private int statusCode;
	private String exception;
	// An optional error code
	private String errorCode;
	// Error message
	private String message;
	// The URI that the request was targeted to
	private String targetUri;

	public ErrorResponse() {
		
	}
	
	public ErrorResponse(int statusCode, String errorCode, String exception, String message, String targetUri) {
		this.statusCode = statusCode;
		this.exception = exception;
		this.errorCode = errorCode;
		this.message = message;
		this.targetUri = targetUri;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTargetUri() {
		return targetUri;
	}

	public void setTargetUri(String targetUri) {
		this.targetUri = targetUri;
	}
}

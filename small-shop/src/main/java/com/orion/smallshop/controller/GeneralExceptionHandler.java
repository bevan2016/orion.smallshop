package com.orion.smallshop.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orion.smallshop.errors.ErrorResponse;
import com.orion.smallshop.errors.SmallShopException;

@RestController
@ControllerAdvice
public class GeneralExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	protected ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception e) {
		logger.error("Request: " + req.getRequestURL() + " raised " + e);
		if (logger.isDebugEnabled())
			logger.debug(getStackTrace(e));

		String exception = null;
		String message = "";
		String errorCode = "";
		if (e != null) {
			exception = e.getClass().getName();
			message = e.getMessage();
			if (e instanceof SmallShopException)
				errorCode = ((SmallShopException) e).getErrorCode().getCode();
		}

		//@TODO this is not accurate
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse report = new ErrorResponse(status.value(), errorCode, exception, message, req.getRequestURL().toString());
		return new ResponseEntity<ErrorResponse>(report, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private static String getStackTrace(Throwable t) {
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			t.printStackTrace(pw);
			return sw.toString();
		}
		return "";
	}
}

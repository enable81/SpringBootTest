package com.onbrid.test.springboot.springboottest.exception;


import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;

import java.io.Serial;
import java.util.Arrays;

public class OnBridException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8513689133709788950L;

	private int code = OnBridProperties.ERROR_CODE_FAIL_VALUE;

	private String[] args = null;

	private String message = null;

	private String detailMessage = null;

	public OnBridException() {
		super();
	}

	public OnBridException(Throwable th)
	{
		super(th);
	}

	public OnBridException(int code) {
		this.code = code;
	}

	public OnBridException(int code, String[] args) {
		this.code = code;
		this.args = args;
	}

	public OnBridException(int code, String detailMessage, String[] args) {
		this.code = code;
		this.detailMessage = detailMessage;
		this.args = args;
	}

	public OnBridException(String message) {
		this.message = message;
	}

	public OnBridException(String message, String[] args) {
		this.message = message;
		this.args = args;
	}

	public OnBridException(String message, String detailMessage) {
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public OnBridException(String message, String[] args, String detailMessage) {
		this.message = message;
		this.args = args;
		this.detailMessage = detailMessage;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {		
		return this.message;
	}
	
	public String getDetailMessage() {		
		return this.detailMessage;
	}
	
	public String[] getArgs() {
		return this.args;
	}
	
	public String toString()
	{
		return this.getClass().getName() + " [Code = " + this.code + "] [Arguments = " + Arrays.toString(this.args) + "] [Message = " + this.message + "] [Detail Message = " + this.detailMessage + "] ";
	}
}
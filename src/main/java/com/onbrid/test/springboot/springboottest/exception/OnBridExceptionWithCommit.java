package com.onbrid.test.springboot.springboottest.exception;


import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;

import java.io.Serial;
import java.util.Arrays;

public class OnBridExceptionWithCommit extends OnBridException {


	@Serial
	private static final long serialVersionUID = -418847265528219884L;

	private int code = OnBridProperties.ERROR_CODE_FAIL_VALUE;

	private String[] args = null;

	private String message = null;

	private String detailMessage = null;

	public OnBridExceptionWithCommit() {
		super();
	}

	public OnBridExceptionWithCommit(Throwable th)
	{
		super(th);
	}

	public OnBridExceptionWithCommit(int code) {
		this.code = code;
	}

	public OnBridExceptionWithCommit(int code, String[] args) {
		this.code = code;
		this.args = args;
	}

	public OnBridExceptionWithCommit(int code, String detailMessage, String[] args) {
		this.code = code;
		this.detailMessage = detailMessage;
		this.args = args;
	}

	public OnBridExceptionWithCommit(String message) {
		this.message = message;
	}

	public OnBridExceptionWithCommit(String message, String[] args) {
		this.message = message;
		this.args = args;
	}

	public OnBridExceptionWithCommit(String message, String detailMessage) {
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public OnBridExceptionWithCommit(String message, String[] args, String detailMessage) {
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
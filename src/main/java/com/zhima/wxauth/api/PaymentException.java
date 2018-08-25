package com.zhima.wxauth.api;

import java.io.Serializable;

public class PaymentException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String returnCode;
	private final String returnMsg;
	
	public PaymentException(String returnCode, String returnMsg) {
		super(returnMsg);
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}
}
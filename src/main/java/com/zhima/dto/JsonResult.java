package com.zhima.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class JsonResult<T>  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返回的数据，例如查询结果")
	private T result;
	
	@ApiModelProperty(value = "操作是否成功")
	private Boolean success;
	
	@ApiModelProperty(value = "错误码，20000为成功")
	private Integer errorCode;
	
	@ApiModelProperty(value = "错误信息")
	private String msg;
	
	public JsonResult() {
		this.success=true;
		this.msg="success";
		this.errorCode=20000;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

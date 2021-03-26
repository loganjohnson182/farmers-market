package com.farmers.market.response;

import io.swagger.annotations.ApiModelProperty;

public class ErrorResponse {

	@ApiModelProperty(required = true, notes = "Error code")
	private Integer code;
	@ApiModelProperty(required = true, notes = "Brief summary of error")
	private String message;
	@ApiModelProperty(required = false, notes = "Detailed description of error")
	private String description;

	public ErrorResponse(Integer code, String message, String description) {
		super();
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public ErrorResponse(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
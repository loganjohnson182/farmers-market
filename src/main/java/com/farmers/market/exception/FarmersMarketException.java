package com.farmers.market.exception;

public class FarmersMarketException extends Exception {
	/**  */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private String description;

	public FarmersMarketException(final int code, final String message, final String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
}
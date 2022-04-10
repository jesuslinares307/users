package com.globallogic.users.exceptions;

import java.time.LocalDateTime;

/**
 * Class CustomException extends Exception
 * 
 * @author Jesus Linares
 * 
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int code;
	protected String userMessage;
	protected String systemMessage;
	protected LocalDateTime timestamp;

	public CustomException(int code, String userMessage, String systemMessage) {
		super();
		this.code = code;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
		this.timestamp = LocalDateTime.now();
	}

	public int getCode() {
		return code;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}

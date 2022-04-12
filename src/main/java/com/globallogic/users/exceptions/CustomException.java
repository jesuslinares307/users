package com.globallogic.users.exceptions;

import java.time.LocalDateTime;

/**
 * Class CustomException extends Exception
 * 
 * @author Jesus Linares
 * 
 */
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	private String userMessage;
	private String systemMessage;
	private LocalDateTime timestamp;

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

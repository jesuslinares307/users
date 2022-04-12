package com.globallogic.users.exceptions;

import com.globallogic.users.enums.ExceptionCodes;

public class UserEmailAlreadyExistsException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.EMAIL_USER_ALREADY_EXIST;

	public UserEmailAlreadyExistsException() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}

}

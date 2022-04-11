package com.globallogic.users.exceptions;

import com.globallogic.users.enums.ExceptionCodes;

public class UserEmailAlreadyExistiException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.EMAIL_USER_ALREADY_EXIST;

	public UserEmailAlreadyExistiException() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}

}

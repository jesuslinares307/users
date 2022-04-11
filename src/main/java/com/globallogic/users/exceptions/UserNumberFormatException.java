package com.globallogic.users.exceptions;

import com.globallogic.users.enums.ExceptionCodes;

public class UserNumberFormatException extends CustomException {

	private static final long serialVersionUID = -50510158400280774L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.NUMBER_FORMAT_EXCEPTION;

	public UserNumberFormatException() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}
}

package com.globallogic.users.exceptions;

import com.globallogic.users.enums.ExceptionCodes;

public class UserAlreadyExist extends CustomException {

	private static final long serialVersionUID = -50510158400280774L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.USER_ALREADY_EXIST;

	public UserAlreadyExist() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}

}

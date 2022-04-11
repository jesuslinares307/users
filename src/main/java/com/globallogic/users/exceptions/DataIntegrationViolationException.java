package com.globallogic.users.exceptions;

import com.globallogic.users.enums.ExceptionCodes;

public class DataIntegrationViolationException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.DATA_INTEGRATION_VIOLATION_EXCEPTION;

	public DataIntegrationViolationException(String cause) {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), cause);
	}

}

package com.global.users.exception;

import com.global.users.enums.ExceptionCodes;

public class UserNotFoundException extends CustomException {
	
	private static final long serialVersionUID = -50510158400280774L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.USER_NOT_FOUND;

	public UserNotFoundException() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}

}

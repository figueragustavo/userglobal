package com.global.users.exception;

import com.global.users.enums.ExceptionCodes;

public class EmailExistsException extends CustomException{

	private static final long serialVersionUID = 1L;
	private static final ExceptionCodes exceptionCode = ExceptionCodes.EMAIL_USER_ALREADY_EXIST;

	public EmailExistsException() {
		super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
	}
}

package com.global.users.enums;

public enum ExceptionCodes {
	
	USER_NOT_FOUND(1, "Usuario no encontrada.", "Usuario no encontrada."),
	INVALID_TOKEN(2, "Token invalido.", "Token invalido"), 
	MISSING_TOKEN(3, "Request sin token", "Request sin token"),
	EMAIL_USER_ALREADY_EXIST(4, "El email ingresado ya existe para un usuario", "El email ingresado ya existe para un usuario"),
	NUMBER_FORMAT_EXCEPTION(5,"El numero ingresado es incorrecto","El numero ingresado es incorrecto"),
	DATA_INTEGRATION_VIOLATION_EXCEPTION(6,"Ocurrio un error con la BD contacte con el admnistrador", null),
	USER_ALREADY_EXIST(7, "El usuario ya existe", "Usuario Existente");

	private int code;
	private String userMessage;
	private String systemMessage;

	private ExceptionCodes(int code, String userMessage, String systemMessage) {
		this.code = code;
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	
	

}

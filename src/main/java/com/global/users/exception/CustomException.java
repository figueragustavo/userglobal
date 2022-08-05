package com.global.users.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
	
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

}

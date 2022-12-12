package com.revature.exceptions;

public class NotLoggedInException extends RuntimeException{

	public NotLoggedInException() {
		super("You must be logged in to complete this operation");
	}

	public NotLoggedInException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotLoggedInException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoggedInException(String message) {
		super(message);
	}

	public NotLoggedInException(Throwable cause) {
		super(cause);
	}
}

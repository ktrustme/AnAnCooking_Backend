package com.anan.anancooking.server.exception;

public enum ExceptionEnum {
	NO_NETWORK_CONNECTION (1, "No network connection"),
	NO_RECORD_IN_TABLE(2, "No record in the table"),
	IMAGE_OVERSIZE(3, "The image size is over the limit"),
	NO_ID_INITIALIZED(4, "No recipe id is initialized"),
	NO_INGREDIENTS(5, "No ingredients");
	
	
	private int errno;
	private String message;
	
	/**
	 * Constructor
	 * @param errno the errno of the Exception
	 * @param message the Exception message
	 */
	ExceptionEnum(int errno, String message) {
		this.errno = errno;
		this.message = message;
	}
	
	/**
	 * getError
	 * @return the errno
	 */
	int getErrno() {
		return this.errno;
	}
	
	/**
	 * getMessage
	 * @return the error message
	 */
	String getMessage() {
		return this.message;
	}
	

}

package com.anan.anancooking.server.exception;

public class ServerException extends Exception {
	private ExceptionEnum enumeration;
	
	public ServerException(ExceptionEnum e) {
		this.enumeration = e;
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder(this.enumeration.getErrno());
		sb.append(" ");
		sb.append(this.enumeration.getMessage());
		return sb.toString();
	}

	@Override
	public String getMessage () {
		return this.enumeration.getMessage();
		
	}
	
}

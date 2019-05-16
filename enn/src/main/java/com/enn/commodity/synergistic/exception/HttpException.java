package com.enn.commodity.synergistic.exception;

public class HttpException extends RuntimeException{
	
	public HttpException() {
		super();
	}

	public HttpException(String message) {
		super(message);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}
	

}

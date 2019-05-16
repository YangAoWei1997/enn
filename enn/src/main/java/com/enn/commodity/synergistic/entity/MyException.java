package com.enn.commodity.synergistic.entity;

public class MyException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4873434213819239684L;
	private Integer errno;
    private String error;
	
    
	public Integer getErrno() {
		return errno;
	}
	public void setErrno(Integer errno) {
		this.errno = errno;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public MyException(Integer errno, String error) {
		super(error);
		this.errno = errno;
		this.error = error;
	}
	
	public MyException() {
		super();
		
	}

    
}
package com.my.project.exceptions;

public class EmptyFieldException extends Exception { 
	private static final long serialVersionUID = 1L;

	public EmptyFieldException(String errorMessage) {
        super(errorMessage);
    }
    
    public EmptyFieldException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

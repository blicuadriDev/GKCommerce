package com.godknows.gkcommerce.services.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}

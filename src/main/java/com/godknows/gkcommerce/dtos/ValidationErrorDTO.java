package com.godknows.gkcommerce.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{
	
	public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	private List<FieldMessageDTO> errors = new ArrayList<>();

	
	public List<FieldMessageDTO> getErrors() {
		return errors;
	}
	
	
	public void addError (String fieldName, String  message) {
		errors.add(new FieldMessageDTO(fieldName, message));
	}
	
}

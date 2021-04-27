package com.auto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Manufacturer not found")
public class ManufacturerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3387516993334229948L;
	
	public ManufacturerNotFoundException(String message)
    {    	
        super(message);
    }
	
}

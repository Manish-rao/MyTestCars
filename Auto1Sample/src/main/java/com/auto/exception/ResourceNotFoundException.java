package com.auto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	  private String resourceName;
	  private String fieldName;
	  private Object fieldValue;

}

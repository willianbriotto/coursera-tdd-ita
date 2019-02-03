package org.com.tddcamelcase;

public class CamelCaseFormatException extends RuntimeException {
	
	public CamelCaseFormatException(String msg) {
		super(new String().format("The string %s does not have a valid format", 
				msg));
	}
	
}

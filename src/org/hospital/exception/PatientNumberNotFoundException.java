package org.hospital.exception;

public class PatientNumberNotFoundException extends RuntimeException{
	public PatientNumberNotFoundException(String message) {
		super(message);
	}

}

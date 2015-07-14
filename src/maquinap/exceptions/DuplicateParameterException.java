package maquinap.exceptions;

public class DuplicateParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1487990162712565590L;

	public DuplicateParameterException() {
		super("The name of the parameter already exists");
	}

}

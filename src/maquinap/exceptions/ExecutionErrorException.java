package maquinap.exceptions;

public class ExecutionErrorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -670654988879036079L;

	public ExecutionErrorException() {
		super("Instruction could not be executed");
	}

}

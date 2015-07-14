package maquinap.exceptions;

import maquinap.instrucciones.Instruccion;

public class InstructionUnimplementedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7986297390413520813L;

	public InstructionUnimplementedException(Instruccion instr) {
		super(instr.getClass().toString() + " could not be executed");
	}
	
	public InstructionUnimplementedException() {
		super("Operation not implemented");
	}

}

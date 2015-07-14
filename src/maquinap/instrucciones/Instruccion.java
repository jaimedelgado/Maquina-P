package maquinap.instrucciones;

import maquinap.MaquinaP;
import maquinap.exceptions.ExecutionErrorException;
import maquinap.exceptions.InstructionUnimplementedException;

public abstract class Instruccion {

	protected Formato format = null;

	public boolean tryParse(String operation)
			throws InstructionUnimplementedException {
		return format.tryParse(operation);
	}

	public void ejecuta(MaquinaP context)
			throws InstructionUnimplementedException, ExecutionErrorException {
		throw new InstructionUnimplementedException(this);
	}
	
	public String toString(){
		return format.toString();
	}

}

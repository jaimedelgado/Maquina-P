package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;

public class AEntero extends Instruccion {

	public AEntero() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("a_entero");
	}

	public void ejecuta(MaquinaP maquina) {
		
		maquina.increasePC();
	}
}

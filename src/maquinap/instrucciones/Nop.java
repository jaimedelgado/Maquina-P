package maquinap.instrucciones;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;

public class Nop extends Instruccion {
	public Nop() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("");
	}

	public void ejecuta(MaquinaP maquina) {
		maquina.increasePC();
	}

}

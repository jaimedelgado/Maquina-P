package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;

public class Desapila extends Instruccion {

	public Desapila() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("desapila");
	}

	public void ejecuta(MaquinaP maquina) {
		maquina.pop();
		maquina.increasePC();
	}

}

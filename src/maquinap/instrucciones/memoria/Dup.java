package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.instrucciones.Instruccion;

public class Dup extends Instruccion {

	public Dup() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("dup");
	}

	public void ejecuta(MaquinaP maquina) {
		Valor value = maquina.pop();
		maquina.push(value);
		maquina.push(value);
		
		maquina.increasePC();
	}
}

package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Modulo extends Instruccion {

	public Modulo() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("modulo");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operador1 = new ValorNum(maquina.pop());
		ValorNum operador2 = new ValorNum(maquina.pop());

		maquina.push(operador1.modulo(operador2));
		
		maquina.increasePC();
	}
}

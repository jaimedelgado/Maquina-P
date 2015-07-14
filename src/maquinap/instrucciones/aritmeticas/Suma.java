package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Suma extends Instruccion {

	public Suma() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("suma");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operando1 = new ValorNum(maquina.pop());
		ValorNum operando2 = new ValorNum(maquina.pop());

		maquina.push(operando1.suma(operando2));
		
		maquina.increasePC();
	}
}

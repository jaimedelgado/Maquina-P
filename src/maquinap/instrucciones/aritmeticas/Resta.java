package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Resta extends Instruccion {
	
	public Resta() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("resta");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operando1 = new ValorNum(maquina.pop());
		ValorNum operando2 = new ValorNum(maquina.pop());

		maquina.push(operando2.resta(operando1));

		maquina.increasePC();
	}

}

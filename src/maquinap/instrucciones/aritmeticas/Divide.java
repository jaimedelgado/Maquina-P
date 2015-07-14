package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.ValorNum;

public class Divide extends Instruccion {

	public Divide() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("divide");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum divisor = new ValorNum(maquina.pop());
		ValorNum dividendo = new ValorNum(maquina.pop());

		maquina.push(dividendo.divide(divisor));
		
		maquina.increasePC();
	}
}

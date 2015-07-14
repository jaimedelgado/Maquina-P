package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Multiplica extends Instruccion {

	public Multiplica() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("multiplica");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operador1 = new ValorNum(maquina.pop());
		ValorNum operador2 = new ValorNum(maquina.pop());

		maquina.push(operador2.multiplica(operador1));

		maquina.increasePC();
	}

}

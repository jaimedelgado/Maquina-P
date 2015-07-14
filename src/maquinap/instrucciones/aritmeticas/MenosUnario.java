package maquinap.instrucciones.aritmeticas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class MenosUnario extends Instruccion {
	
	public MenosUnario() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("menos_unario");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operando1 = new ValorNum(maquina.pop());

		maquina.push(operando1.negativo());

		maquina.increasePC();
	}

}

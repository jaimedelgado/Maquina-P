package maquinap.instrucciones.booleanas;

import maquinap.MaquinaP;
import maquinap.instrucciones.Instruccion;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;

public class MayorOIgual extends Instruccion {
	
	public MayorOIgual() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("mayor_o_igual");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum valor1 = new ValorNum(maquina.pop());
		ValorNum valor2 = new ValorNum(maquina.pop());

		int result = (valor2.getInnerValue().doubleValue() >= valor1.getInnerValue().doubleValue())?1:0;
		ValorNum valorResult = new ValorNum(result);
		
		maquina.push(valorResult);
		
		maquina.increasePC();
	}

}

package maquinap.instrucciones.booleanas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Distinto extends Instruccion {

	public Distinto() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("distinto");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum valor1 = new ValorNum(maquina.pop());
		ValorNum valor2 = new ValorNum(maquina.pop());

		Number n1 = valor1.getInnerValue().doubleValue();
		Number n2 = valor2.getInnerValue().doubleValue();
		
		int result = 1;
		
		if(n1 != n2){
			result = 1;
		}
		else{
			result = 0;
		}
				
		ValorNum valorResult = new ValorNum(result);

		maquina.push(valorResult);

		maquina.increasePC();
	}
}

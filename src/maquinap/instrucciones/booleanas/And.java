package maquinap.instrucciones.booleanas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.ValorNum;

public class And extends Instruccion {
	public And() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("and");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorNum operador1 = new ValorNum(maquina.pop());
		ValorNum operador2 = new ValorNum(maquina.pop());
		
		int result = -1; 
		
		if(operador1.getInnerValue().intValue() == 1 && operador2.getInnerValue().intValue() == 1){
			result = 0;
		}
		else{
			result = 1;
		}	
		
		ValorNum valorResult = new ValorNum(result);
		
		maquina.push(valorResult);
		maquina.increasePC();

	}
}

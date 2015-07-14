package maquinap.instrucciones.booleanas;

import maquinap.MaquinaP;
import maquinap.instrucciones.Instruccion;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;

public class Not extends Instruccion {
	public Not() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("not");
	}

	public void ejecuta(MaquinaP maquina) {
		
		ValorNum valor = new ValorNum(maquina.pop());
		
		int result = -1;
		
		if(valor.getInnerValue().intValue() == 0){
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

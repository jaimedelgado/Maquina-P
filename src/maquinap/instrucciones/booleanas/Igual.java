package maquinap.instrucciones.booleanas;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Igual extends Instruccion {
	
	public Igual() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("igual");
	}

	public void ejecuta(MaquinaP maquina) {
	
		ValorNum valor1 = new ValorNum(maquina.pop());
		ValorNum valor2 = new ValorNum(maquina.pop());
				
		ValorNum valorResult = null;
		
		boolean v1IsNull = valor1 == null 
						|| valor1.getInnerValue() == null 
						|| valor1.getInnerValue().equals(0) 
						|| valor1.getStringValue().equals("0") 
						|| valor1.getStringValue().equals("0.0") 
						|| valor2.getStringValue().equals("NULL");
		
		boolean v2IsNull = valor2 == null 
						|| valor2.getInnerValue() == null 
						|| valor2.getInnerValue().equals(0) 
						|| valor2.getStringValue().equals("0") 
						|| valor2.getStringValue().equals("0.0") 
						|| valor2.getStringValue().equals("NULL");
		
		if(v1IsNull && v2IsNull){
			valorResult = new ValorNum(1);
		}
		else{
			
			Number n1 = valor1.getInnerValue().doubleValue();
			Number n2 = valor2.getInnerValue().doubleValue();
			
			int result = (n1.equals(n2))?1:0;
			
			valorResult = new ValorNum(result);
		}
		
		maquina.push(valorResult);
		maquina.increasePC();
		
	}
}

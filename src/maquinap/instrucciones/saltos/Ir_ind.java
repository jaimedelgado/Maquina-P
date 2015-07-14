package maquinap.instrucciones.saltos;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.ValorEnt;

public class Ir_ind extends Instruccion {
	
	public Ir_ind() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("ir_ind");
	}

	public void ejecuta(MaquinaP maquina) {
		maquina.setPC(new ValorEnt(maquina.pop()).toInt());
	}

}

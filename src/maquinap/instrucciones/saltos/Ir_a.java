package maquinap.instrucciones.saltos;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.ValorNum;

public class Ir_a extends Instruccion {

	public Ir_a() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("ir_a ValorEnt valor");
	}

	public void ejecuta(MaquinaP maquina) {
		maquina.setPC(((ValorNum) this.format.getParam("valor")).toInt());
	}
}

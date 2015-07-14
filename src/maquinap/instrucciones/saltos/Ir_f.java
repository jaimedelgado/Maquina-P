package maquinap.instrucciones.saltos;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.ValorEnt;

public class Ir_f extends Instruccion {
	public Ir_f() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("ir_f ValorEnt direccion");
	}

	public void ejecuta(MaquinaP maquina) {
		ValorEnt valor = new ValorEnt(maquina.pop());

		if (valor.toInt() == 0) {
			maquina.setPC(((ValorEnt) this.format.getParam("direccion")).toInt());
		}
		else{
			maquina.increasePC();
		}
	}

}

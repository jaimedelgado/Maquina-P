package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorEnt;
import maquinap.instrucciones.Instruccion;

public class Free extends Instruccion {
	public Free() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("free ValorEnt tam");
	}

	public void ejecuta(MaquinaP maquina) {

		int amount = ((ValorEnt) this.format.getParam("tam")).toInt();
		int dir = (new ValorEnt(maquina.pop())).toInt();
		maquina.getMemoriaDinamica().free(dir, amount);

		maquina.increasePC();
	}

}

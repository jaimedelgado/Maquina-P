package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.ValorEnt;
import maquinap.instrucciones.Instruccion;

public class Alloc extends Instruccion {
	public Alloc() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("alloc ValorEnt tam");
	}

	public void ejecuta(MaquinaP maquina) {

		int amount = ((ValorEnt) this.format.getParam("tam"))
				.toInt();
		
		int dir = maquina.getMemoriaDinamica().alloc(amount);
		ValorEnt allocDir = new ValorEnt(dir);
		maquina.push(allocDir);

		maquina.increasePC();
	}

}

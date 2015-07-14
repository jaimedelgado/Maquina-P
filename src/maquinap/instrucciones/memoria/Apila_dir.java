package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Apila_dir extends Instruccion {

	public Apila_dir() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("apila_dir ValorEnt direccion");
	}

	public void ejecuta(MaquinaP maquina) {
		
		int dir = ((ValorNum)this.format.getParam("direccion")).toInt();
		Valor value = maquina.getMemoriaDinamica().getValor(dir);
		maquina.push(value);

		maquina.increasePC();
	}

}

package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Desapila_dir extends Instruccion {

	public Desapila_dir() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("desapila_dir ValorEnt direccion");
	}

	public void ejecuta(MaquinaP maquina) {

		int dir = ((ValorNum) this.format.getParam("direccion")).toInt();
		Valor value = maquina.pop();
		maquina.getMemoriaDinamica().setValor(dir, value);

		maquina.increasePC();
	}

}

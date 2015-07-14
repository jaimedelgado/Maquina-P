package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Desapila_ind extends Instruccion {

	public Desapila_ind() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("desapila_ind");
	}

	public void ejecuta(MaquinaP maquina) {
		Valor valor = maquina.pop();
		int dir = (new ValorNum(maquina.pop())).toInt();
		maquina.getMemoriaDinamica().setValor(dir, valor);

		maquina.increasePC();
	}

}

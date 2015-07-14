package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorNum;
import maquinap.instrucciones.Instruccion;

public class Apila_ind extends Instruccion {

	public Apila_ind() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("apila_ind");
	}

	public void ejecuta(MaquinaP maquina) {
		
		int dir = (new ValorNum(maquina.pop())).toInt();
		Valor valor = maquina.getMemoriaDinamica().getValor(dir);
		maquina.push(valor);

		maquina.increasePC();
	}

}

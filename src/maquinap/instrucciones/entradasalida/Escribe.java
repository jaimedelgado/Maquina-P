package maquinap.instrucciones.entradasalida;

import maquinap.MaquinaP;
import maquinap.instrucciones.Instruccion;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;

public class Escribe extends Instruccion {

	public Escribe() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("out");
	}

	public void ejecuta(MaquinaP maquina) {
		Valor v = maquina.pop();
		//v = maquina.getMemoriaDinamica().getValor(Integer.parseInt((String) v.getInnerValue()));
		if(v == null){
			System.out.println("NULL");
		}
		else{
			System.out.println(v.toString());
		}
		maquina.increasePC();
	}
}

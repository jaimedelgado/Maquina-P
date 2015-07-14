package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorEnt;
import maquinap.instrucciones.Instruccion;

public class Clona extends Instruccion {
	public Clona() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("clona ValorEnt cantidad");
	}

	public void ejecuta(MaquinaP maquina) {
		int origen = new ValorEnt(maquina.pop()).toInt();
		int destino = new ValorEnt(maquina.pop()).toInt();
		int amount = new ValorEnt(this.format.getParam("cantidad")).toInt();
		
		for(int i = 0; i < amount; i++){
			Valor value = maquina.getMemoriaDinamica().getValor(origen+i);
			maquina.getMemoriaDinamica().setValor(destino+i, value);
		}
		
		maquina.increasePC();
	}

}

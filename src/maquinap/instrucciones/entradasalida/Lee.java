package maquinap.instrucciones.entradasalida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.ExecutionErrorException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.instrucciones.Instruccion;
import maquinap.valores.Valor;

public class Lee extends Instruccion {

	public Lee() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("in");
	}

	public void ejecuta(MaquinaP maquina) throws ExecutionErrorException {
		System.out.print("Enter value: ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
	    try {
			s = bufferRead.readLine();
		} catch (IOException e) {
			throw new ExecutionErrorException();
		}
	    Valor valor = new Valor();
	    valor.setValue(s);
	    maquina.push(valor);
	    
	    
		maquina.increasePC();
	}

}

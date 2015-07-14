package maquinap.instrucciones.memoria;

import maquinap.MaquinaP;
import maquinap.exceptions.DuplicateParameterException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Formato;
import maquinap.valores.Valor;
import maquinap.valores.ValorEnt;
import maquinap.instrucciones.Instruccion;

public class Apila extends Instruccion {

	public Apila() throws InstructionUnimplementedException,
			DuplicateParameterException {
		this.format = new Formato("apila Valor valor");
	}

	public void ejecuta(MaquinaP maquina) {
		Valor val = this.format.getParam("valor");
		
		if(val.toString().startsWith("[") && val.toString().endsWith("]")){
			int dir = -1;
			try{
				dir = MaquinaP.TagManager.getInstance().getTagDir(val.toString());
			}
			catch(NullPointerException e){
				System.err.println("Tag no existe");
				throw e;
			}
			maquina.push(new ValorEnt(dir));
		}
		else{
			maquina.push(this.format.getParam("valor"));
		}
		maquina.increasePC();
	}

}

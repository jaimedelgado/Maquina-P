package maquinap.instrucciones;

import java.util.HashMap;
import java.util.Map;

import maquinap.exceptions.DuplicateParameterException;
import maquinap.valores.Valor;

public class Formato {
	
	// The string represents the name
	private Map<String, Valor> parameters = null;
	// The order of the parameters
	private String[] order;
	private String fullOperation = "";
	private String instruccion = "";
	
	public Formato(String formato) throws DuplicateParameterException{
		this.parameters = new HashMap<String, Valor>();
		
		String[] tokens = formato.split(" ");
		this.instruccion = tokens[0];
		order = new String[tokens.length-1];
		for(int i = 1; i < tokens.length - 1; i = i+2){
			if(parameters.containsKey(tokens[i+1])){
				throw new DuplicateParameterException();
			}
			parameters.put(tokens[i+1], Valor.GetValor(tokens[i]));
			order[i-1] = tokens[i+1];
		}
	}

	public boolean tryParse(String operation) {
		operation = operation.trim();
		String[] tokens = operation.split(" ");
		
		if(!tokens[0].equalsIgnoreCase(instruccion)){
			return false; // First word is not the one that should
		}
		
		if(tokens.length - 1 != parameters.size()){
			return false; // Not enough or too many params
		}
		
		for(int i = 0; i < tokens.length - 1; i++){
			if(!parameters.get(order[i]).setValue(tokens[i+1])){
				return false; // Parameter could not be converted to this value
			}
		}
		
		fullOperation = operation;
		return true;
	}

	public Valor getParam(String paramName) {
		return parameters.get(paramName);
	}
	
	public String toString(){
		return fullOperation;
	}
}

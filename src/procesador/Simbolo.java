package procesador;

import ast.Tipable;

public class Simbolo {
	
	public int bloque;
	public String id;
	public Tipable vinculo;
	
	public Simbolo(String id, Tipable vinculo, int bloque) {
		this.id = id;
		this.vinculo = vinculo;
		this.bloque = bloque;
	}
}

package procesador;

import alex.AnalizadorLexicoTiny;
import ast.Programa;

public class Procesador {
	
	private AnalizadorLexicoTiny alt;
	
	public Procesador(AnalizadorLexicoTiny lexico) {
		alt = lexico;
	}
	
	public String procesa(Programa p) {
		Vinculador vinculador = new Vinculador();
		vinculador.vincula(p.getDeclaraciones(), p.getInstrucciones());
		
		Chequeador chequeador = new Chequeador();
		chequeador.chequea(p.getDeclaraciones(), p.getInstrucciones());
		
		Asignador asignador = new Asignador();
		asignador.asigna(p, p.decs, p.ins);
		
		Generador generador = new Generador();
		generador.genera(p, p.decs, p.ins);
		
		return p.cod;
	}
}

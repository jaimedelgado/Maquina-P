package ast;

import java.util.ArrayList;
import java.util.List;


public class Casos extends Nodo {
	public Casos casos;
	public Caso caso;
	public static Casos seqCompuestaCasos(Casos casos, Caso caso, int fila){
		Casos c = new Casos();
		c.fila=fila;
		c.casos=casos;
		c.caso = caso;
		return c;
	}
	public static Casos seqSimpleCasos(Caso caso, int fila){
		Casos c = new Casos();
		c.fila=fila;
		c.caso = caso;
		return c;
	}
	public List<Caso> getCasos() {
		List<Caso> listaCasos = new ArrayList<Caso>();
		
		if(casos != null) {
			listaCasos.addAll(casos.getCasos());
		}
		listaCasos.add(caso);
		
		return listaCasos;
	}
}

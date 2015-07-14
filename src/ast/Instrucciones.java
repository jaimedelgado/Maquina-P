package ast;

import java.util.ArrayList;
import java.util.List;

public class Instrucciones extends Nodo {
	public Instrucciones instrucciones;
	public Instruccion instruccion;
	
	public int comienzo;
	public int fin;
	
	public static Instrucciones seqCompuestaIs(Instrucciones instrucciones, Instruccion instruccion, int fila){
		Instrucciones i = new Instrucciones();
		i.fila = fila;
		i.instrucciones = instrucciones;
		i.instruccion = instruccion;
		return i;
	}
	public static Instrucciones seqSimpleIs(Instruccion instruccion, int fila){
		Instrucciones i = new Instrucciones();
		i.fila = fila;
		i.instruccion = instruccion;
		return i;
	}
	public List<Instruccion> getInstrucciones() {
		List<Instruccion> l = new ArrayList<Instruccion>();
		
		if(instrucciones != null) {
			l.addAll(instrucciones.getInstrucciones());
		}
		l.add(instruccion);
		
		return l;
	}
}
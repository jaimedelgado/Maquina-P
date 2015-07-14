package ast;

import java.util.List;

public class Programa extends Nodo {
	
	public static String SEPARATOR = System.getProperty("line.separator");

	public static int cinst;
	public static int dir;
	public static int nivel;
	
	public int finDatos;
	
	public Decs decs;
	public Instrucciones ins;
	
	public static Programa programa(Decs decs,  Instrucciones ins, int fila){
		Programa p = new Programa();
		p.decs = decs;
		p.ins = ins;
		p.fila = fila;
		return p;
	}

	public List<Declaracion> getDeclaraciones() {
		return decs.ldecs.getDeclaraciones();
	}

	public List<Instruccion> getInstrucciones() {
		return ins.getInstrucciones();
	}
}
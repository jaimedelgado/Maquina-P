package ast;

public class IOut extends Nodo {
public Designador designador;
	
	public static IOut iout(Designador d, int fila){
		IOut w = new IOut();
		w.fila = fila;
		w.designador = d;
		return w;
	}
}

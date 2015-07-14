package ast;

public class IFree extends Nodo {
public Designador designador;
	
	public static IFree ifree(Designador designador, int fila){
		IFree d = new IFree();
		d.fila = fila;
		d.designador = designador;
		return d;
	}
}

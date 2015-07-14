package ast;

public class IIn extends Nodo {
	public Designador designador;
	
	public static IIn iin(Designador designador, int fila){
		IIn r = new IIn();
		r.fila = fila;
		r.designador = designador;
		return r;
	}
}

package ast;

public class IAsig extends Nodo {
	public Designador designador;
	public Exp0 exp0;
	
	public static IAsig iasig(Designador designador, Exp0 exp0, int fila){
		IAsig a = new IAsig();
		a.fila = fila;
		a.designador = designador;
		a.exp0 = exp0;
		return a;
	}
}

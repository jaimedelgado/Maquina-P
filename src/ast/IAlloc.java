package ast;

public class IAlloc extends Nodo {
public Designador designador;
	
	public static IAlloc ialloc(Designador designador, int fila){
		IAlloc n = new IAlloc();
		n.fila = fila;
		n.designador = designador;
		return n;
	}
}

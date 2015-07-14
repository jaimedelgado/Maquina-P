package ast;

public class IWhile extends Nodo {

	public Exp0 exp0;
	public Is ins;
	
	public static IWhile iwhile(Exp0 exp0, Is ins, int fila){
		IWhile b = new IWhile();
		b.fila = fila;
		b.exp0 = exp0;
		b.ins = ins;
		return b;
	}
}

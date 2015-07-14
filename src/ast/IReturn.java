package ast;

public class IReturn extends Nodo {
	public Exp0 exp0;

	public static IReturn iReturn(Exp0 exp0, int fila){
		IReturn i = new IReturn();
		i.fila=fila;
		i.exp0=exp0;
		return i;
	}
}

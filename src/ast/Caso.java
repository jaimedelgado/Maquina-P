package ast;

public class Caso extends Nodo {
	public Exp0 exp0;
	public Is is;
	
	public int comienzo = -1;
	public int fin = -1;

	public static Caso caso(Exp0 exp0, Is is, int fila){
		Caso c = new Caso();
		c.fila=fila;
		c.exp0=exp0;
		c.is=is;
		return c;
	}
}

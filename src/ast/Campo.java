package ast;

public class Campo extends Nodo {

	public DecVar decV;
	public DecMet decMet;
	public int desp=-1;
	
	public static Campo campo(DecVar decV, int fila){
		Campo c = new Campo();
		c.fila = fila;
		c.decV = decV;
		c.decMet = null;
		return c;
	}

	public static Campo campoMet(DecMet decMet, int fila){
		Campo c = new Campo();
		c.fila = fila;
		c.decMet = decMet;
		c.decV = null;
		return c;
	}
}

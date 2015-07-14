package ast;

public class Op1 extends Nodo {
	
	public enum Tipos{ MAS, MENOS, OR}
	
	public Tipos tipo;
	
	// Op1 -> +
	public static Op1 op1Mas(int fila) {
		Op1 o = new Op1();
		o.fila = fila;
		o.tipo = Tipos.MAS;
		return o;
	}

	// Op1 -> -
	public static Op1 op1Menos(int fila) {
		Op1 o = new Op1();
		o.fila = fila;
		o.tipo = Tipos.MENOS;
		return o;
	}

	// Op1 -> or
	public static Op1 op1Or(int fila) {
		Op1 o = new Op1();
		o.fila = fila;
		o.tipo = Tipos.OR;
		return o;
	}
}

package ast;

public class Op2 extends Nodo {
	public enum Tipos{ POR, DIV, MOD, AND}
	
	public Tipos tipo;
	
	// Op2 -> *
	public static Op2 op2Por(int fila) {
		Op2 o = new Op2();
		o.fila = fila;
		o.tipo = Tipos.POR;
		return o;
	}

	// Op2 -> /
	public static Op2 op2Div(int fila) {
		Op2 o = new Op2();
		o.fila = fila;
		o.tipo = Tipos.DIV;
		return o;
	}

	// Op2 -> %
	public static Op2 op2Mod(int fila) {
		Op2 o = new Op2();
		o.fila = fila;
		o.tipo = Tipos.MOD;
		return o;
	}

	// Op2 -> and
	public static Op2 op2And(int fila) {
		Op2 o = new Op2();
		o.fila = fila;
		o.tipo = Tipos.AND;
		return o;
	}
}

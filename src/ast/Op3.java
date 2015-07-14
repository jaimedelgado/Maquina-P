package ast;

public class Op3 extends Nodo {
	public enum Tipos{ MENOS, NOT}
	
	public Tipos tipo;
	
	// Op3 -> -
	public static Op3 op3Menos(int fila) {
		Op3 o = new Op3();
		o.fila = fila;
		o.tipo = Tipos.MENOS;
		return o;
	}

	// Op3 -> not
	public static Op3 op3Not(int fila) {
		Op3 o = new Op3();
		o.fila = fila;
		o.tipo = Tipos.NOT;
		return o;
	}
}

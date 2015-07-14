package ast;

public class Op3NA extends Nodo {
	public enum Tipos{INT, REAL}
	
	public Tipos tipo;
	
	// Op3NA -> (int)
	public static Op3NA op3naCastInt(int fila) {
		Op3NA o = new Op3NA();
		o.fila = fila;
		o.tipo = Tipos.INT;
		return o;
	}

	// Op3NA -> (real)
	public static Op3NA op3naCastReal(int fila) {
		Op3NA o = new Op3NA();
		o.fila = fila;
		o.tipo = Tipos.REAL;
		return o;
	}
}

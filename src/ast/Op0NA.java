package ast;

public class Op0NA extends Nodo {
public enum Tipos{ EQ, NEQ, LT, GT, LE, GE}
	
	public Tipos tipo;

	// Op0NA -> ==
	public static Op0NA op0naIgual(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.EQ;
		return o;
	}

	// Op0NA -> !=
	public static Op0NA op0naDistinto(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.NEQ;
		return o;
	}

	// Op0NA -> <
	public static Op0NA op0naMenor(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.LT;
		return o;
	}

	// Op0NA -> >
	public static Op0NA op0naMayor(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.GT;
		return o;
	}

	// Op0NA -> <=
	public static Op0NA op0naMenorIgual(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.LE;
		return o;
	}

	// Op0NA -> >=
	public static Op0NA op0naMayorIgual(int fila) {
		Op0NA o = new Op0NA();
		o.fila = fila;
		o.tipo = Tipos.GE;
		return o;
	}
}

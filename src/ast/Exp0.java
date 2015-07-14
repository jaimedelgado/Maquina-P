package ast;

public class Exp0 extends Exp {
	
	// Exp0 -> Exp1 Op0NA Exp1
	public static Exp0 exp01(Exp1 exp11, Op0NA op, Exp1 exp12, int fila) {
		Exp0 e = new Exp0();
		e.fila = fila;
		e.exp11 = exp11;
		e.exp12 = exp12;
		e.op0na = op;
		e.tipoResultante = TiposResult.NUMENTERO;
		e.tipoExp = TiposExp.EXP1EXP1;
		return e;
	}
	
	// Exp0 -> Exp1
	public static Exp0 exp02(Exp1 exp1, int fila) {
		Exp0 e = new Exp0();
		e.fila = fila;
		e.tipoResultante = exp1.tipoResultante;
		e.exp11 = exp1;
		e.tipoExp = TiposExp.EXP1;
		return e;
	}
}

package ast;

public class Exp2 extends Exp {
	
	// Exp2 -> Exp2 Op2 Exp3
	public static Exp2 exp21(Exp2 exp2, Op2 op2, Exp3 exp3, int fila) {
		Exp2 e = new Exp2();
		e.fila = fila;
		e.exp22 = exp2;
		e.op2 = op2;
		e.exp3 = exp3;
		switch (op2.tipo) {
		case AND:
			e.tipoResultante = TiposResult.NUMENTERO;
			break;
		case DIV:
			if (exp2.tipoResultante == TiposResult.NUMREAL || exp3.tipoResultante == TiposResult.NUMREAL) {
				e.tipoResultante = TiposResult.NUMREAL;
			} else {
				e.tipoResultante = TiposResult.NUMENTERO;
			}
			break;
		case MOD:
			e.tipoResultante = TiposResult.NUMENTERO;
			break;
		case POR:
			if (exp2.tipoResultante == TiposResult.NUMREAL || exp3.tipoResultante == TiposResult.NUMREAL) {
				e.tipoResultante = TiposResult.NUMREAL;
			} else {
				e.tipoResultante = TiposResult.NUMENTERO;
			}
			break;
		}
		e.tipoExp = TiposExp.EXP2EXP3;
		return e;
	}

	// Exp2 -> Exp3
	public static Exp2 exp22(Exp3 exp3, int fila) {
		Exp2 e = new Exp2();
		e.fila = fila;
		e.exp3 = exp3;
		e.tipoResultante = exp3.tipoResultante;
		e.tipoExp = TiposExp.EXP3;
		return e;
	}
}

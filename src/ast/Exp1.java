package ast;

public class Exp1 extends Exp {
	
	// Exp1 -> Exp1 Op1 Exp2
	public static Exp1 exp11(Exp1 exp1, Op1 op1, Exp2 exp2, int fila) {
		Exp1 e = new Exp1();
		e.fila = fila;
		e.exp1 = exp1;
		e.op1 = op1;
		e.exp2 = exp2;
		switch(op1.tipo){
		case MAS:
			if (exp1.tipoResultante == TiposResult.NUMREAL || exp2.tipoResultante == TiposResult.NUMREAL) {
				e.tipoResultante = TiposResult.NUMREAL;
			} else {
				e.tipoResultante = TiposResult.NUMENTERO;
			}
			break;
		case MENOS:
			if (exp1.tipoResultante == TiposResult.NUMREAL || exp2.tipoResultante == TiposResult.NUMREAL) {
				e.tipoResultante = TiposResult.NUMREAL;
			} else {
				e.tipoResultante = TiposResult.NUMENTERO;
			}
			break;
		case OR:
			e.tipoResultante = TiposResult.NUMENTERO;
			break;
		}
		e.tipoExp = TiposExp.EXP1EXP2;
		return e;
	}

	// Exp1 -> Exp2
	public static Exp1 exp12(Exp2 exp2, int fila) {
		Exp1 e = new Exp1();
		e.fila = fila;
		e.exp2 = exp2;
		e.tipoResultante = exp2.tipoResultante;
		e.tipoExp = TiposExp.EXP2;
		return e;
	}
}

package ast;

public class Exp3 extends Exp {
	
	// Exp3 -> Op3 Exp3
	public static Exp3 exp31(Op3 op3, Exp3 exp3, int fila){
		Exp3 e = new Exp3();
		e.fila = fila;
		e.op3 = op3;
		e.exp33 = exp3;
		switch(op3.tipo){
		case MENOS:
			e.tipoResultante = exp3.tipoResultante;
			break;
		case NOT:
			e.tipoResultante = Exp.TiposResult.NUMENTERO;
			break;
		}
		e.tipoExp = TiposExp.OP3EXP3;
		return e;
	}
	
	// Exp3 -> Op3NA Exp4
	public static Exp3 exp32(Op3NA op3na, Exp4 exp4, int fila){
		Exp3 e = new Exp3();
		e.fila = fila;
		e.op3na = op3na;
		e.exp4 = exp4;
		switch(op3na.tipo){
		case INT:
			e.tipoResultante = Exp.TiposResult.NUMENTERO;
			break;
		case REAL:
			e.tipoResultante = Exp.TiposResult.NUMREAL;
			break;
		}
		e.tipoExp = TiposExp.OP3NAEXP4;
		return e;
	}
	
	// Exp3 -> Exp4
	public static Exp3 exp33(Exp4 exp4, int fila){
		Exp3 e = new Exp3();
		e.fila = fila;
		e.exp4 = exp4;
		e.tipoResultante = exp4.tipoResultante;
		e.tipoExp = TiposExp.EXP4;
		return e;
	}
}

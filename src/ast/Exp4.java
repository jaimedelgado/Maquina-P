package ast;

public class Exp4 extends Exp {
	
	// Exp4 -> numeroEntero
	public static Exp4 numEntero(String numeroEntero, int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = TiposResult.NUMENTERO;
		e.numeroEntero = numeroEntero;
		e.tipoExp = TiposExp.BASICO;
		return e;
	}
	
	// Exp4 -> numeroReal
	public static Exp4 numReal(String numeroReal, int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = TiposResult.NUMREAL;
		e.numeroReal = numeroReal;
		e.tipoExp = TiposExp.BASICO;
		return e;
	}
	
	// Exp4 -> null
	public static Exp4 eNull(int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = TiposResult.NULL;
		e.tipoExp = TiposExp.BASICO;
		return e;
	}
	
	// Exp4 -> Designador
	public static Exp4 mem(Designador designador, int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = TiposResult.DESIG;
		e.designador = designador;
		e.tipoExp = TiposExp.DESIGNADOR;
		return e;
	}
	
	//Exp4 -> Invocación a Función o Método
	public static Exp4 invocFunMet(InvocacionFunMet invoc, int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = TiposResult.INVOC;
		e.invocFunMet = invoc;
		e.tipoExp = TiposExp.FUNMET;
		return e;
	}
	
	// Exp4 -> (Exp0)
	public static Exp4 exp41(Exp0 exp0, int fila){
		Exp4 e = new Exp4();
		e.fila = fila;
		e.tipoResultante = exp0.tipoResultante;
		e.exp0 = exp0;
		e.tipoExp = TiposExp.EXP0;
		return e;
	}
}

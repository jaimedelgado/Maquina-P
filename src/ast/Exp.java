package ast;

public class Exp extends Nodo {
	
	//Exp0
	public Exp1 exp11;
	public Exp1 exp12;
	public Op0NA op0na;
	
	//Exp1
	public Exp1 exp1;
	public Op1 op1;
	public Exp2 exp2;
	
	//Exp2
	public Exp2 exp22;
	public Op2 op2;
	public Exp3 exp3;
	
	//Exp3
	public Op3 op3;
	public Exp3 exp33;
	
	//Exp3
	public Op3NA op3na;
	public Exp4 exp4;
	
	//Exp4
	public String numeroEntero;
	public String numeroReal;
	public Designador designador;
	public InvocacionFunMet invocFunMet;
	public Exp0 exp0;
	
	public enum TiposResult {
		NUMENTERO, NUMREAL, NULL, DESIG, INVOC
	}

	public TiposResult tipoResultante;

	public enum TiposExp {
		EXP1EXP1, EXP1, EXP1EXP2, EXP2, EXP2EXP3, EXP3, OP3EXP3, OP3NAEXP4, EXP4, BASICO, DESIGNADOR, EXP0, FUNMET
	}
	
	public DTipo0 dtipo0;

	public TiposExp tipoExp;
}

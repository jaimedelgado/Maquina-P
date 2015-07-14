package ast;

public class InvocacionFunMet extends Nodo {
	public Designador designador;
	public ParamsInvoc paramsinvoc;
	
	//public Declaracion vinculo;
	public int numInstruccionesPaso = 0;
	
	public static InvocacionFunMet llamada(Designador d, ParamsInvoc p, int fila){
		InvocacionFunMet i = new InvocacionFunMet();
		i.fila=fila;
		i.designador = d;
		i.paramsinvoc = p;
		return i;
	}
}

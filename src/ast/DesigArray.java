package ast;

public class DesigArray extends Nodo {
	public Designador designador;
	public Exp0 exp0;
	public Declaracion vinculo;
	public static DesigArray indxElem(Designador designador, Exp0 exp0, int fila){
		DesigArray d = new DesigArray();
		d.designador=designador;
		d.exp0=exp0;
		d.fila=fila;
		return d;
	}
}

package ast;

public class DesigPuntero extends Nodo {
	public Designador designador;
	public Declaracion vinculo;
	public static DesigPuntero deref(Designador designador, int fila){
		DesigPuntero d =new DesigPuntero();
		d.designador=designador;
		d.fila=fila;
		return d;
	}
}

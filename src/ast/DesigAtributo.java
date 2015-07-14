package ast;

public class DesigAtributo extends Nodo {
	public Designador designador;
	public String id;
	public Declaracion vinculo;
	public static DesigAtributo selCampo(Designador designador, String id, int fila){
		DesigAtributo d = new DesigAtributo();
		d.designador = designador;
		d.id=id;
		d.fila=fila;
		return d;
	}
}

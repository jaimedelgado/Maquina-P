package ast;

public class DTipoPuntero extends Nodo {

	public DTipo0 dtipo0;
	public boolean simplificado = false;
	public Declaracion vinculo;
	public static DTipoPuntero pointer(DTipo0 dtipo0, int fila){
		DTipoPuntero p = new DTipoPuntero();
		p.dtipo0 = dtipo0;
		p.fila = fila;		
		return p;
	}
}

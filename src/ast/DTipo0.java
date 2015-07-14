package ast;

public class DTipo0 extends Nodo {
	public enum Tipo{ INT, REAL, REFID, REG, ARRAY, PUNTERO, OBJETO}
	public Tipo tipo;
	public boolean simplificado = false;
	public DTipo1 dtipo1;
	public DTipoPuntero puntero;
	public Declaracion vinculo;
	
	public int tam = -1;
	
	public static DTipo0 dTipoPuntero(DTipoPuntero puntero, int fila){
		DTipo0 d = new DTipo0();
		d.puntero = puntero;
		d.fila = fila;
		d.dtipo1 = null;
		d.tipo = Tipo.PUNTERO;
		return d;
	}
	
	public static DTipo0 dTipo1(DTipo1 dtipo1, int fila){
		DTipo0 d = new DTipo0();
		d.dtipo1 = dtipo1;
		d.puntero = null;
		d.tipo = dtipo1.tipo;
		d.fila = fila;
		d.tipo = dtipo1.tipo;
		return d;
	}
}

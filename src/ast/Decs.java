package ast;

public class Decs extends Nodo {
	
	public LDecs ldecs;
	
	public static Decs listDecs(LDecs listaDeclaraciones, int fila){
		Decs l = new Decs();
		l.fila = fila;
		l.ldecs = listaDeclaraciones;
		return l;
	}
	
	public static Decs listDecsVacia(int fila){
		Decs l = new Decs();
		l.fila = fila;
		return l;
	}
}

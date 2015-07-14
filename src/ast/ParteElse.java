package ast;

public class ParteElse extends Nodo {

	public Is is;
	
	public int comienzo = -1;
	public int fin = -1;
	
	public static ParteElse pElse(Is is, int fila){
		ParteElse pe = new ParteElse();
		pe.fila = fila;
		pe.is = is;
		return pe;
	}
	
	public static ParteElse sinpElse(int fila){
		ParteElse pe = new ParteElse();
		pe.fila = fila;
		return pe;
	}
}

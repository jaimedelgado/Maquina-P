package ast;

public class Superclase extends Nodo {

	public String id;
	public Declaracion vinculo;
	public int tam=-1;
	public static Superclase superclase(String id, int fila){
		Superclase s = new Superclase();
		s.id = id;
		s.fila = fila;		
		return s;
	}
	
	public static Superclase superclaseVacia(int fila){
		Superclase s = new Superclase();
		s.id = null;
		s.fila = fila;		
		return s;
	}
}

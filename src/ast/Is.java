package ast;

public class Is extends Nodo {
	public Instrucciones is;

	public static Is instrucciones(Instrucciones is, int fila){
		Is i= new Is();
		i.fila=fila;
		i.is = is;
		return i;
	}
	public static Is sinInstrucciones(int fila){
		Is i= new Is();
		i.fila=fila;
		return i;
	}
}

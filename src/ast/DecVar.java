package ast;

public class DecVar extends Declaracion {
	
	public int desp;
	
	public static DecVar decVariable(DTipo0 dtipo0, String id, int fila){
		DecVar d = new DecVar();
		d.fila = fila;
		d.id = id;
		d.dtipo0 = dtipo0;
		d.clase = Clases.VARIABLE;
		return d;
	}
}

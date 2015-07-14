package ast;

public class DecTipo extends Declaracion {


	public static DecTipo decTipo(DTipo0 dtipo0, String id, int fila){
		DecTipo d = new DecTipo();
		d.fila = fila;
		d.id = id;
		d.dtipo0 = dtipo0;
		d.clase = Clases.TIPO;
		return d;
	}
}

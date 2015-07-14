package ast;

public class DecMet extends Declaracion {

	public static int comienzo = -1;

	public int dirComienzo;
		
	public Cabecera cabecera;
	public Programa programa;

	public int tamParamMasVariables=-1;
	
	public static DecMet decMet(Cabecera cabecera, Programa programa, String id, int fila){
		DecMet dm = new DecMet();
		dm.fila = fila;
		dm.id = id;
		dm.cabecera = cabecera;
		dm.programa = programa;
		dm.dtipo0 = cabecera.tiporet.dtipo0;
		dm.clase = Clases.METODO;
		return dm;
	}
	
}

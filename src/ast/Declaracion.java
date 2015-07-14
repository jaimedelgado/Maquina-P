package ast;

public class Declaracion extends Tipable {
	
	public enum Clases{VARIABLE, FUNCION, TIPO, METODO, PARAMETRO}

	public String id;
	public Clases clase;
	
	public int nivel; // variable, parámetro, procedimiento
	public int dir; // variable, parámetro
	
	public DecVar decV;
	public DecTipo decT;
	public DecFun decFun;
	
	public static Declaracion decV(DecVar decv, int fila){
		Declaracion d = new Declaracion();
		d.decV = decv;
		d.fila = fila;	
		d.clase=Clases.VARIABLE;
		d.dtipo0 = decv.dtipo0;
		d.id = decv.id;
		return d;
	}
	
	public static Declaracion decT(DecTipo dect, int fila){
		Declaracion d = new Declaracion();
		d.decT = dect;
		d.fila = fila;
		d.clase=Clases.TIPO;
		d.dtipo0 = dect.dtipo0;
		d.id = dect.id;
		return d;
	}
	
	public static Declaracion decF(DecFun decfun, int fila){
		Declaracion d = new Declaracion();
		d.decFun = decfun;
		d.fila = fila;
		d.dtipo0=decfun.dtipo0;
		d.clase=Clases.FUNCION;
		d.id=decfun.id;
		return d;
	}
}

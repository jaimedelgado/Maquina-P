package ast;

public class Cabecera extends Nodo {

	public String id;
	public Parametros params;
	public TipoRet tiporet;
	public static Cabecera cabe(String id, Parametros params, TipoRet tipo, int fila){
		Cabecera c = new Cabecera();
		c.id = id;
		c.params = params;
		c.fila = fila;
		c.tiporet = tipo;
		return c;
	}
	
	public Parametros getParametros(){
		return params;
	}
}

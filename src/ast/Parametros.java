package ast;

public class Parametros extends Nodo {
	
	public LParametros listaParametros;
	
	// Parametros -> ( LParametros )
	public static Parametros params(LParametros listaParametros, int fila) {
		Parametros ps = new Parametros();
		ps.fila = fila;
		ps.listaParametros = listaParametros;
		return ps;
	}

	// Parametros -> ( )
	public static Parametros paramsVacios(int fila) {
		Parametros ps = new Parametros();
		ps.fila = fila;
		ps.listaParametros = null;
		return ps;
	}
}

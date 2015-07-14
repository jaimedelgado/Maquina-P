package ast;

public class Parametro extends Declaracion {
	
	public enum Tipos{VALOR, VARIABLE}
	
	public String id;
	public DTipo0 dtipo0;
	public Tipos tipoParametro;
	
	// Parametro -> DTipo0 id
	public static Parametro param(DTipo0 dtipo0, String id, int fila) {
		Parametro p = new Parametro();
		p.fila = fila;
		p.tipoParametro = Tipos.VALOR;
		p.id = id;
		p.dtipo0 = dtipo0;
		p.clase = Clases.PARAMETRO;
		return p;
	}

	// Parametro -> & DTipo0 id
	public static Parametro paramRef(DTipo0 dtipo0, String id, int fila) {
		Parametro p = new Parametro();
		p.fila = fila;
		p.tipoParametro = Tipos.VARIABLE;
		p.id = id;
		p.dtipo0 = dtipo0;
		p.clase=Clases.PARAMETRO;
		return p;
	}
}

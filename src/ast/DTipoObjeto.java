package ast;

public class DTipoObjeto extends Nodo {

	public Superclase superclase;
	public Campos lcamposomet;
	public boolean simplificado = false;
	public Declaracion vinculo;
	public int tam = -1;
	public String cod="";
	public static DTipoObjeto objeto(Superclase superclase, Campos lcamposomet, int fila){
		DTipoObjeto o = new DTipoObjeto();
		o.fila = fila;
		o.superclase = superclase;
		o.lcamposomet = lcamposomet;
		return o;
	}
}

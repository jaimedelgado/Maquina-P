package ast;

public class DTipoRegistro extends Nodo {

	public LCampos listaCampos;
	public boolean simplificado = false;
	public Declaracion vinculo;
	
	public static DTipoRegistro registro(LCampos listaCampos, int fila){
		DTipoRegistro r = new DTipoRegistro();
		r.listaCampos = listaCampos;
		r.fila = fila;		
		return r;		
	}
}

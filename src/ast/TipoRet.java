package ast;

public class TipoRet extends Nodo {

	//tReturn((DTipo0)dtipo0, parser.fila())
	public DTipo0 dtipo0;
	
	public static TipoRet tReturn(DTipo0 dtipo0, int fila){
		TipoRet tr = new TipoRet();
		tr.fila = fila;
		tr.dtipo0 = dtipo0;
		return tr;
	}
	
	public static TipoRet sinReturn(int fila){
		TipoRet tr = new TipoRet();
		tr.fila = fila;
		return tr;
	}
	
}

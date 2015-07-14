package ast;

public class ParamsInvoc extends Nodo {

	public LParamsInvoc listaParametros;
	
	//paramsInvoc((LParamsInvoc)l, parser.fila())
	public static ParamsInvoc paramsInvoc(LParamsInvoc listaParametros, int fila) {
		ParamsInvoc ps = new ParamsInvoc();
		ps.fila = fila;
		ps.listaParametros = listaParametros;
		return ps;
	}

	//paramsInvocVacios(parser.fila())
	public static ParamsInvoc paramsInvocVacios(int fila) {
		ParamsInvoc ps = new ParamsInvoc();
		ps.fila = fila;
		ps.listaParametros = null;
		return ps;
	}
}

package ast;

import java.util.ArrayList;
import java.util.List;

public class LParametros extends Nodo {
	
	public LParametros listaParametros;
	public Parametro parametro;
	
	// LParametros -> LParametros , Parametro
	public static LParametros seqCompuestaParams(LParametros listaParametros, Parametro parametro, int fila) {
		LParametros p = new LParametros();
		p.fila = fila;
		p.listaParametros = listaParametros;
		p.parametro = parametro;
		return p;
	}

	// ListaParametros -> Parametro
	public static LParametros seqSimpleParams(Parametro parametro, int fila) {
		LParametros p = new LParametros();
		p.fila = fila;
		p.parametro = parametro;
		return p;
	}
	
	public List<Parametro> getParametros(){
		List<Parametro> listaParam = new ArrayList<Parametro>();
		
		if(listaParametros != null){
			listaParam.addAll(listaParametros.getParametros());
		}
		listaParam.add(parametro);
		
		return listaParam;
	}
}

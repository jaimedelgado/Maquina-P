package ast;

import java.util.ArrayList;
import java.util.List;

public class LParamsInvoc extends Nodo {

	public LParamsInvoc listaExp0s;
	public Exp0 exp0;
	
	public static LParamsInvoc seqCompuestaExps(LParamsInvoc listaExp0s, Exp0 exp0, int fila) {
		LParamsInvoc p = new LParamsInvoc();
		p.fila = fila;
		p.listaExp0s = listaExp0s;
		p.exp0 = exp0;
		return p;
	}

	public static LParamsInvoc seqSimpleExps(Exp0 exp0, int fila) {
		LParamsInvoc p = new LParamsInvoc();
		p.fila = fila;
		p.exp0 = exp0;
		return p;
	}
	
	public List<Exp0> getExp0s(){
		List<Exp0> listaParam = new ArrayList<Exp0>();
		
		if(listaExp0s != null){
			listaParam.addAll(listaExp0s.getExp0s());
		}
		listaParam.add(exp0);
		
		return listaParam;
	}
}

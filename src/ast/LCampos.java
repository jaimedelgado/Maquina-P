package ast;

import java.util.ArrayList;
import java.util.List;

public class LCampos extends Nodo {
	public LCampos lcampos;
	public DecVar decvar;
	public static LCampos seqCompuestaVars(LCampos lcampos, DecVar decvariable, int fila){
		LCampos l = new LCampos();
		l.fila=fila;
		l.lcampos=lcampos;
		l.decvar=decvariable;
		return l;
	}
	public static LCampos seqSimpleVars(DecVar decvariable, int fila){
		LCampos l = new LCampos();
		l.fila=fila;
		l.decvar=decvariable;
		return l;
	}
	public List<DecVar> getCampos(){
		List<DecVar> l = new ArrayList<DecVar>();
		
		if(lcampos != null){
			l.addAll(lcampos.getCampos());
		}
		l.add(decvar);
		
		return l;
	}
	public DecVar consulta(String id){
		List<DecVar> campos = getCampos();
		for(DecVar c : campos){
			if( c.decV.id.equals(id)){
				return c;
			}
		}
		return null;
	}
}

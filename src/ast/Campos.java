package ast;

import java.util.ArrayList;
import java.util.List;

public class Campos extends Nodo {
	
	public Campos lcamposomet;
	public Campo campo;
	
	public static Campos seqCompuestaCampos(Campos lcamposomet, Campo campo, int fila) {
		Campos l = new Campos();
		l.fila = fila;
		l.lcamposomet = lcamposomet;
		l.campo = campo;
		return l;
	}

	public static Campos seqSimpleCampos(Campo campo, int fila) {
		Campos l = new Campos();
		l.fila = fila;
		l.campo = campo;
		return l;
	}
	
	public List<Campo> getCampos(){
		List<Campo> listaCamp = new ArrayList<Campo>();
		
		if(lcamposomet != null){
			listaCamp.addAll(lcamposomet.getCampos());
		}
		listaCamp.add(campo);
		
		return listaCamp;
	}
	public Campo consulta(String id){
		List<Campo> campos = getCampos();
		for(Campo c : campos){
			if(c.decV!=null && c.decV.id.equals(id)){
				return c;
			}else if(c.decMet!=null && c.decMet.id.equals(id)){
				return c;
			}
		}
		return null;
	}
}

package ast;

import java.util.ArrayList;
import java.util.List;

public class LDecs extends Nodo {
	
	public LDecs listaDeclaraciones;
	public Declaracion declaracion;
	
	// LDecs -> LDecs ; Declaracion
	public static LDecs seqCompuestaDecs(LDecs listaDeclaraciones, Declaracion declaracion, int fila) {
		LDecs p = new LDecs();
		p.fila = fila;
		p.listaDeclaraciones = listaDeclaraciones;
		p.declaracion = declaracion;
		return p;
	}

	// listaDeclaraciones -> Declaracion
	public static LDecs seqSimpleDecs(Declaracion declaracion, int fila) {
		LDecs p = new LDecs();
		p.fila = fila;
		p.declaracion = declaracion;
		return p;
	}
	
	public List<Declaracion> getDeclaraciones(){
		List<Declaracion> listaDec = new ArrayList<Declaracion>();
		
		if(listaDeclaraciones != null){
			listaDec.addAll(listaDeclaraciones.getDeclaraciones());
		}
		listaDec.add(declaracion);
		
		return listaDec;
	}
}

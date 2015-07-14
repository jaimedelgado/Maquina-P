package procesador;

import java.util.List;

import ast.Campo;
import ast.DTipo1;
import ast.DecFun;
import ast.DecTipo;
import ast.DecVar;
import ast.Declaracion;
import ast.DTipo0.Tipo;
import ast.Declaracion.Clases;
import ast.Decs;
import ast.Instrucciones;
import ast.Parametro;
import ast.Programa;
import ast.DTipo0;

public class Asignador {
	
	public void asigna(Programa programa, Decs declaraciones, Instrucciones instrucciones) {
		asignaEspacio(programa, declaraciones, instrucciones);
	}
	// Programa
	
	public void asignaEspacio(Programa programa, Decs declaraciones, Instrucciones instrucciones) {
		
		for (Declaracion declaracion : declaraciones.ldecs.getDeclaraciones()) {
			asignaTamanyo(declaracion);
		}
		
		programa.finDatos = anidamiento(declaraciones.ldecs.getDeclaraciones()) + tamVariables(declaraciones.ldecs.getDeclaraciones());
		Programa.dir = anidamiento(declaraciones.ldecs.getDeclaraciones()) + 1;
		Programa.nivel = 0;
		for (Declaracion declaracion : declaraciones.ldecs.getDeclaraciones()) {
			asignaEspacio(declaracion);
		}
	}

	private int tamVariables(List<Declaracion> declaraciones) {
		int tam = 0;
		for (Declaracion declaracion : declaraciones) {
			if(declaracion.clase == Clases.VARIABLE){
				
				if(declaracion.dtipo0.dtipo1!=null){tam += declaracion.dtipo0.dtipo1.tam;}
				else{
					tam += declaracion.dtipo0.tam;
				}
				//System.out.println(tam);
			}			
		}
		return tam;
	}

	public int anidamiento(List<Declaracion> declaraciones) {
		int anidamiento = 0;
		for (Declaracion declaracion : declaraciones) {
			anidamiento = Math.max(anidamiento, anidamientoDe(declaracion));
		}
		return anidamiento;
	}

	public int anidamientoDe(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			return (1 + anidamiento(((DecFun) d.decFun).programa.decs.ldecs.getDeclaraciones()));
		/*case METODO:
			return 0;*/
		case PARAMETRO:
			return 0;
		case TIPO:
			return 0;
		case VARIABLE:
			return 0;
		default:
			return 0;
		}
	}

	// Declaraciones

	public void asignaEspacio(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			asignaEspacio((DecFun) d.decFun);
			break;
		case PARAMETRO:
			// Do nothing
			break;
		case TIPO:
			// Do nothing
			break;
		case VARIABLE:
			asignaEspacio((DecVar) d.decV);
			break;
			
			
		/*case METODO:
			// Do nothing
			break;*/
		default:
			break;

		}
	}

	private void asignaEspacio(DecVar d) {
		d.nivel = Programa.nivel;
		d.dir = Programa.dir;
		//asignaTamanyo(d.tipo);
		if(d.dtipo0.dtipo1!=null){ Programa.dir = Programa.dir + d.dtipo0.dtipo1.tam; } 
		else{Programa.dir = Programa.dir + d.dtipo0.tam;}
	}

	private void asignaEspacio(DecFun d) {
		int copiaDir = Programa.dir;
		int copiaNivel = Programa.nivel;
		Programa.nivel += 1;
		d.nivel = Programa.nivel;
		Programa.dir = 0;
		if (d.cabecera.params.listaParametros != null) {
			for (Parametro param : d.cabecera.params.listaParametros.getParametros()) {
				param.dir = Programa.dir;
				param.nivel = Programa.nivel;
				//asignaTamanyo(param.tipo);
				if (param.tipoParametro == Parametro.Tipos.VARIABLE) {
					Programa.dir += 1;
				} else {
					Programa.dir = Programa.dir + param.dtipo0.tam;
				}
			}
		}
		for (Declaracion declaracion : d.programa.decs.ldecs.getDeclaraciones()) {
			asignaEspacio(declaracion);
		}
		Programa.nivel = copiaNivel;
		Programa.dir = copiaDir;
	}

	// Definiciones de tipo

	private void asignaTamanyo(DTipo0 t) {
		switch (t.tipo) {
			case PUNTERO:
				asignaTamanyo(t.puntero.dtipo0);
				t.tam = 1;
				break;
			default:
				break;
		}
		if(t.dtipo1!=null){asignaTamanyo(t.dtipo1);}
	}
	
	
	private void asignaTamanyo(DTipo1 t){
		switch (t.tipo) {
			case ARRAY:
				if (t.tam == -1) {
					asignaTamanyo(t.tipoArray);
					t.tam = t.tipoArray.tam * t.valTamArray;
				}
				break;
			case INT:
				t.tam = 1;
				break;
			case REFID:
				GestorErrores.error("Referencias a id deberían haber sido simplificadas anteriormente", t);
				break;
			case REG:
				if (t.tam == -1) {
					t.tam = 0;
					for (DecVar decV : t.registro.listaCampos.getCampos()) {
						decV.desp = t.tam;
						asignaTamanyo(decV.dtipo0);
						t.tam = t.tam + decV.dtipo0.dtipo1.tam;
					}
				}
				break;
			case OBJETO:
				if (t.objeto.tam == -1) {
					 if (t.objeto.superclase == null){
						 t.objeto.tam = 2; // la primera celda apuntará a la clase del objeto
						 // la segunda a la superclase
					 }else{
							 t.objeto.tam = t.objeto.superclase.tam;
					 }
					 for(Campo c: t.objeto.lcamposomet.getCampos()) {
						 c.desp = t.objeto.tam;
						 if (c.decMet!=null) {
							 asignaEspacio(c.decMet);
						 }else if(c.decV!=null && c.decV.dtipo0.tipo==Tipo.OBJETO){
							 t.objeto.tam++;
						 }
					 }
					 
				} 
				break;
			default:
				break;
		}
	}
	
	public void asignaTamanyo(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			asignaTamanyoFun((DecFun)d.decFun);
			break;
		case PARAMETRO:
			asignaTamanyo(d.dtipo0);
			break;
		case TIPO:
			asignaTamanyo(d.decT.dtipo0);
			break;
		case VARIABLE:
			asignaTamanyoVar((DecVar)d.decV);
			break;
		default:
			break;

		}
	}

	/*private void asignaTamanyo(Parametro d) {
		asignaTamanyo(d.dtipo0);
	}*/

	private void asignaTamanyoVar(DecVar d) {
		d.nivel = Programa.nivel;
		d.dir = Programa.dir;
		asignaTamanyo(d.dtipo0);
		if(d.dtipo0.dtipo1!=null){Programa.dir = Programa.dir + d.dtipo0.dtipo1.tam;}
		else{
			Programa.dir = Programa.dir + d.dtipo0.tam;
		}
	}

	private void asignaTamanyoFun(DecFun d) {
		if(d.tamParamMasVariables != -1){
			return;
		}
		d.tamParamMasVariables = 0;
		if (d.cabecera.params.listaParametros != null) {
			for (Parametro param : d.cabecera.params.listaParametros.getParametros()) {
				asignaTamanyo(param.dtipo0);
				d.tamParamMasVariables += param.dtipo0.tam;
			}
		}
		
		for (Declaracion declaracion : d.programa.decs.ldecs.getDeclaraciones()) {
			asignaEspacio(declaracion);
			if(declaracion.clase == Clases.VARIABLE){
				d.tamParamMasVariables += declaracion.decV.dtipo0.dtipo1.tam;
			}
			else if(declaracion.clase == Clases.FUNCION){
				asignaTamanyoFun((DecFun)declaracion.decFun);
			}
		}
	}
}

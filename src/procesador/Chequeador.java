package procesador;

import java.util.ArrayList;
import java.util.List;

import ast.Cabecera;
import ast.Campo;
import ast.Campos;
import ast.Caso;
import ast.Casos;
import ast.DTipo0;
import ast.DTipo0.Tipo;
import ast.DTipo1;
import ast.DTipoObjeto;
import ast.DTipoPuntero;
import ast.DTipoRegistro;
import ast.DecFun;
import ast.DecMet;
import ast.DecTipo;
import ast.DecVar;
import ast.Declaracion;
import ast.Declaracion.Clases;
import ast.Decs;
import ast.Designador;
import ast.Exp;
import ast.Exp.TiposResult;
import ast.Exp0;
import ast.IAlloc;
import ast.IAsig;
import ast.IFree;
import ast.IIf;
import ast.IIn;
import ast.IOut;
import ast.IReturn;
import ast.IWhile;
import ast.Instruccion;
import ast.Instrucciones;
import ast.InvocacionFunMet;
import ast.Is;
import ast.LCampos;
import ast.LDecs;
import ast.LParametros;
import ast.Parametro;
import ast.Parametros;
import ast.ParteElse;
import ast.Programa;

public class Chequeador {

	public void chequea(List<Declaracion> declaraciones,
			List<Instruccion> instrucciones) {
		for (Declaracion d : declaraciones) {
			chequea(d);
			simplificaDefTipos(d);
		}

		for (Instruccion i : instrucciones){
			chequea(i);
		}
	}

	// Declaraciones

	private void chequea(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			chequea(d.decFun);
			break;
		case TIPO:
			chequea(d.decT);
			break;
		case VARIABLE:
			chequea(d.decV);
		case PARAMETRO:
			chequea(d.dtipo0);
			break;
		default:
			break;
		}
	}

	private void chequea(DecVar d) {
		chequea(d.dtipo0);
	}

	private void chequea(DecTipo d) {
		chequea(d.dtipo0);
	}

	private void chequea(DecFun d) {
		if(d.dtipo0!=null){	chequea(d.dtipo0);}
		chequea(d.cabecera);
		chequea(d.programa);
		/*List<Declaracion> declaraciones = d.();
		List<Declaracion> parametros = d.getParametros();
		List<Instruccion> instrucciones = d.iBloque.instrucciones
				.getInstrucciones();

		for (Declaracion param : parametros) {
			chequea(param.tipo);
			simplificaDefTipos(param);
		}
		for (Declaracion dec : declaraciones) {
			chequea(dec);
			simplificaDefTipos(dec);
		}
		for (Instruccion i : instrucciones)
			chequea(i);
			*/
	}

	private void chequea(Programa programa) {
		chequea(programa.decs);
		chequea(programa.ins);
		
	}

	private void chequea(Instrucciones ins) {
		List<Instruccion> l = ins.getInstrucciones();
		for(Instruccion i: l){
			chequea(i);
		}
		
	}

	private void chequea(Decs decs) {
		chequea(decs.ldecs);
		
	}

	private void chequea(LDecs ldecs) {
		List<Declaracion> l = ldecs.getDeclaraciones();
		for(Declaracion d: l){
			chequea(d);
		}
		
	}

	private void chequea(Cabecera cabecera) {
		chequea(cabecera.params);
		
	}

	private void chequea(Parametros params) {
		chequea(params.listaParametros);
		
	}

	private void chequea(LParametros listaParametros) {
		if(listaParametros!=null){
			List<Parametro> l = listaParametros.getParametros();
			for(Parametro p: l){
				chequea(p);
			}
		}
		
	}

	private void chequea(Parametro p) {
		chequea(p.dtipo0);
		simplificaDefTipos(p);
		
	}

	private void simplificaDefTipos(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			simplificaDefTipos(d.decFun);
			break;
		case TIPO:
			d.dtipo0 = tipoSimplificado(d.dtipo0);
			break;
		case VARIABLE:
			d.dtipo0 = tipoSimplificado(d.dtipo0);
			break;
		case METODO:
			simplificaDefTipos((DecMet)d);
			break;
		case PARAMETRO:
			d.dtipo0 = tipoSimplificado(d.dtipo0);
		default:
			break;
		}
	}
	private void simplificaDefTipos(DecMet d) {
		simplificaDefTipos(d.cabecera);
		simplificaDefTipos(d.programa);
	}
	private void simplificaDefTipos(DecFun d) {
		simplificaDefTipos(d.cabecera);
		simplificaDefTipos(d.programa);
	}
	private void simplificaDefTipos(Programa programa) {
		simplificaDefTipos(programa.decs);
		
	}

	private void simplificaDefTipos(Decs decs) {
		simplificaDefTipos(decs.ldecs);
		
	}

	private void simplificaDefTipos(LDecs ldecs) {
		if(ldecs!=null){
			for(Declaracion d: ldecs.getDeclaraciones()){
				d.dtipo0 = tipoSimplificado(d.dtipo0);
			}
		}
		
	}

	private void simplificaDefTipos(Parametro d) {
		d.dtipo0 = tipoSimplificado(d.dtipo0);
	}


	private void simplificaDefTipos(Cabecera cabecera) {
		simplificaDefTipos(cabecera.params);
		
	}

	private void simplificaDefTipos(Parametros params) {
		simplificaDefTipos(params.listaParametros);
		
	}

	private void simplificaDefTipos(LParametros listaParametros) {
		if(listaParametros!=null){
			List<Parametro> l = listaParametros.getParametros();
			for(Parametro p: l){
				simplificaDefTipos(p);
			}
		}
	}

	

	private void chequea(DTipo0 t) {
		switch (t.tipo) {
		case INT:
			break;
		case REAL:
			break;
		case REG:
			chequea(t.dtipo1);
			break;
		case REFID:
			chequea(t.dtipo1);
			break;
		case OBJETO:
			chequea(t.dtipo1);
			break;
		case PUNTERO:
			chequea(t.puntero);
			break;
		case ARRAY:
			chequea(t.dtipo1);
			break;
		default:
			break;
		}
	}

	private void chequea(DTipoPuntero puntero) {
		chequea(puntero.dtipo0);
	}

	private void chequea(DTipo1 t) {
		switch (t.tipo) {
		case INT:
			break;
		case REAL:
			break;
		case REG:
			chequea(t.registro);
			break;
		case REFID:
			if (t.vinculo.clase != Clases.TIPO) {
				GestorErrores.error("El identificador debería ser uno de tipo", t);
			}
			break;
		case OBJETO:
			chequea(t.objeto);
			break;
		case ARRAY:
			chequea(t.tipoArray);
			break;
		default:
			break;
		}
		
	}

	private void chequea(DTipoObjeto objeto) {
		if((objeto.superclase.id!=null) && objeto.superclase.vinculo.dtipo0.tipo!=Tipo.OBJETO) {
			GestorErrores.error("La superclase debería ser de tipo", objeto);
		}
		chequea(objeto.lcamposomet);
	}

	private void chequea(Campos lcamposomet) {
		List<Campo> l = lcamposomet.getCampos();
		for(Campo c:l){
			chequea(c);
		}
	}

	private void chequea(Campo c) {
		if(c.decMet==null){
			chequea(c.decV);
		}else{
			chequea(c.decMet);
		}
		
	}

	private void chequea(DTipoRegistro registro) {
		chequea(registro.listaCampos);
		
	}

	private void chequea(LCampos listaCampos) {
		List<DecVar> l = listaCampos.getCampos();
		for(DecVar v:l){
			chequea(v);
		}
		
	}

	// Simplificacion
	private DTipo0 tipoSimplificado(DTipo0 t) {
		switch (t.tipo) {
		case ARRAY:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		case REAL:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		case INT:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		case PUNTERO:
			if(!t.puntero.simplificado){
				t.puntero.dtipo0 = tipoSimplificado(t.puntero.dtipo0);
				t.simplificado = true;
			}
			return t;
		case REFID:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		case REG:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		case OBJETO:
			t.dtipo1 = tipoSimplificado(t.dtipo1);
			return t;
		default:
			return null;
		}
	}

	private DTipo0 tipoSimplificado(DTipoPuntero puntero) {
		return tipoSimplificado(puntero.dtipo0);
	}

	private DTipo1 tipoSimplificado(DTipo1 t) {
		switch (t.tipo) {
		case ARRAY:
			if(!t.tipoArray.simplificado){
				t.tipoArray = tipoSimplificado(t.tipoArray);
				t.tipoArray.simplificado = true;
			}
			return t;
		case REAL:
			return t;
		case INT:
			return t;
		case OBJETO:
			for(Campo c : t.objeto.lcamposomet.getCampos()){
				if(c.decMet!=null){ simplificaDefTipos(c.decMet);}
				else if(c.decV!=null){ c.decV.dtipo0 = tipoSimplificado(c.decV.dtipo0);}
				
			}
			return t;
		case REFID:
			while (t.vinculo!=null && t.vinculo.dtipo0.dtipo1.tipo == Tipo.REFID) {
				t = t.vinculo.dtipo0.dtipo1;
			}
			
			return t;
		case REG:
			if(t.registro!=null){
				for (DecVar c : t.registro.listaCampos.getCampos()) {
					if(!c.dtipo0.simplificado){
						c.dtipo0 = tipoSimplificado(c.dtipo0);
						c.dtipo0.simplificado = true;
					}
				}
			}
			return t;
		default:
			return null;
		}
	}

	// Instrucciones

	private void chequea(Instruccion i) {
		switch (i.tipo) {
		case ASIG:
			chequea(i.iasig);
			break;
		case WHILE:
			chequea(i.iwhile);
			break;
		case IF:
			chequea(i.iif);
			break;
		case FREE:
			chequea(i.ifree);
			break;
		case INVOCACIONFUNMET:
			chequea(i.invocacionfunmet);
			break;
		case ALLOC:
			chequea(i.ialloc);
			break;
		case IN:
			chequea(i.iin);
			break;
		case OUT:
			chequea(i.iout);
			break;
		case RETURN:
			chequea(i.ireturn);
			break;
		default:
			break;
		}
	}

	private void chequea(IReturn ireturn) {
		chequea(ireturn.exp0);
		
	}

	private void chequea(IAsig i) {
		chequea(i.designador);
		chequea(i.exp0);
		if (i.designador.tipo != null && i.exp0.tipoExp != null
				&& !compatibles(i.designador.dtipo0, i.exp0.dtipo0)) {
			GestorErrores.error("Incompatibilidad de tipos en asignación", i);
		}
	}

	private void chequea(IOut i) {
		chequea(i.designador);
		if (i.designador.dtipo0 != null && !tipoPresentable(i.designador.dtipo0)) {
			GestorErrores.error("No es posible escribir valores de este tipo", i);
		}
	}

	private boolean tipoPresentable(DTipo0 tipo) {
		return tipo.tipo == Tipo.REAL
				|| tipo.tipo == Tipo.INT;
	}

	private void chequea(IIn i) {
		chequea(i.designador);
		if (i.designador.tipo != null && !tipoLegible(i.designador.dtipo0)) {
			GestorErrores.error("No es posible leer valores de este tipo", i);
		}
	}

	private boolean tipoLegible(DTipo0 tipo) {
		return tipo.tipo == Tipo.REAL
				|| tipo.tipo == Tipo.INT;
	}

	private void chequea(IAlloc i) {
		chequea(i.designador);
		if (i.designador.tipo != null
				&& i.designador.dtipo0.tipo != Tipo.PUNTERO 
				&& i.designador.dtipo0.tipo != Tipo.OBJETO) {
			GestorErrores.error("El tipo debe ser de tipo puntero o objeto", i);
		}
	}

	private void chequea(IFree i) {
		chequea(i.designador);
		if (i.designador.tipo != null
				&& i.designador.dtipo0.tipo != Tipo.PUNTERO 
				&& i.designador.dtipo0.tipo != Tipo.OBJETO) {
			GestorErrores.error("El tipo debe ser de tipo puntero o objeto", i);
		}
	}

	private void chequea(IIf i) {
		chequea(i.casos);
		chequea(i.parteelse);
	}

	private void chequea(ParteElse parteelse) {
		// TODO Auto-generated method stub
		
	}

	private void chequea(Casos casos) {
		for (Caso c : casos.getCasos()) {
			chequea(c.exp0);
			if (c.exp0.dtipo0 != null && c.exp0.dtipo0.tipo != Tipo.INT) {
				GestorErrores
						.error("El tipo de la expresión debe ser entero", casos);
			}
			chequea(c.is);
		}
	}

	private void chequea(Is is) {
		chequea(is.is);
		
	}

	private void chequea(IWhile i) {
		chequea(i.exp0);
		if (i.exp0.dtipo0 != null && i.exp0.dtipo0.tipo != Tipo.INT) {
			GestorErrores
					.error("El tipo de la expresión debe ser entero", i);
		}
		chequea(i.ins);
	}

	private void chequea(InvocacionFunMet illamada) {
		if (illamada.designador.vinculo.clase == Clases.FUNCION) {
			List<Parametro> parametros;
			List<Exp0> argumentos;
			if (((DecFun) illamada.designador.vinculo).cabecera.params.listaParametros != null) {
				parametros = ((DecFun) illamada.designador.vinculo).cabecera.params.listaParametros.getParametros();
			} else {
				parametros = new ArrayList<Parametro>();
			}
			if (illamada.paramsinvoc.listaParametros != null) {
				argumentos = illamada.paramsinvoc.listaParametros.getExp0s();
			} else {
				argumentos = new ArrayList<Exp0>();
			}

			if (argumentos.size() != parametros.size()) {
				GestorErrores.error("Discordancia en el número de parámetros", illamada);
			} else {
				int i = 0;
				for (Parametro p : parametros) {
					Exp arg = argumentos.get(i);
					chequea(arg);

					if (p.tipoParametro == Parametro.Tipos.VARIABLE
							&& !esDesignador(arg)) {
						GestorErrores.error("El par�metro " + i
								+ " debe ser un designador", illamada);
					} else if (!compatibles(p.dtipo0, arg.dtipo0)) {
						GestorErrores
								.error("Tipos no compatibles en el parámetro "
										+ i, illamada);
					}

					i++;
				}
			}
		} else {
			GestorErrores.error(illamada.designador.id
					+ " se está invocando pero no es un procedimiento", illamada);
		}
	}

	private boolean esDesignador(Exp arg) {
		return arg.tipoResultante == TiposResult.DESIG;
	}

	private boolean compatibles(DTipo0 t1, DTipo0 t2) {
		if (t1 == null || t2 == null || t1.tipo != t2.tipo) {
			return false;
		} else if (t1.tipo == Tipo.REFID || t2.tipo == Tipo.REFID) {
			System.err
					.println("Los tipos deberían estar simplificados antes de consultar compatibilidad");
			return false;
		}

		if (t1.tipo == Tipo.ARRAY) {
			//return compatibles(t1.tipoArray, t2.tipoArray);
			return t1.dtipo1.tipoArray == t2.dtipo1.tipoArray;
		} else if (t1.tipo == Tipo.PUNTERO) {
			if(t1.puntero == null || t2.puntero == null){
				// Cuando un pointer es null no tenemos tipoPointer
				// null es compatible con todos los pointers
				return true;
			}
			else{
				return t1.puntero == t2.puntero;
				//return compatibles(t1.tipoPointer, t2.tipoPointer);
			}
		} else if (t1.tipo == Tipo.REG) {
			// Ya se ha comprobado que tengan la misma cantidad de campos
			List<DecVar> cs1 = t1.dtipo1.registro.listaCampos.getCampos();
			List<DecVar> cs2 = t2.dtipo1.registro.listaCampos.getCampos();
			boolean valid = true;
			int i = 0;
			while (valid && i < cs1.size()) {
				DecVar c1 = cs1.get(i);
				DecVar c2 = cs2.get(i);
				valid = c1.dtipo0 == c2.dtipo0;
				//valid = compatibles(c1.tipo, c2.tipo);
				i++;
			}
			return valid;
		} else {
			// Tipos b�sicos e iguales
			return true;
		}
	}

	// Designadores

	private void chequea(Designador d) {
		DTipo0 t = null;

		switch (d.tipo) {
		case ARRAY:
			chequea(d.desigarray.designador);
			chequea(d.desigarray.exp0);
			if (d.desigarray.designador == null || d.desigarray.exp0 == null) {
				t = null;
			}
			if (d.desigarray.designador.dtipo0.tipo != Tipo.ARRAY){
				GestorErrores.error("El designador debería ser un array", d);
				t = null;
			}
			else if (d.desigarray.exp0.dtipo0.tipo != Tipo.INT) {
				GestorErrores.error("El índice debería ser de tipo entero", d);
				t = null;
			} else {
				t = d.desigarray.designador.dtipo0;
			}
			break;
		case PUNTERO:
			chequea(d.desigpuntero.designador);
			if (d.desigpuntero.designador == null || d.desigpuntero.designador.dtipo0 == null) {
				t = null;
			} else if (d.desigpuntero.designador.dtipo0.tipo == Tipo.PUNTERO) {
				t = d.desigpuntero.designador.dtipo0;
			} else {
				GestorErrores
						.error("El designador debería ser de tipo puntero", d);
			}
			break;
		case ID:
			if (!validoComoDesignador(d.vinculo)) {
				GestorErrores.error("Id debe ser una variable o un parametro", d);
			} else {
				t = tipoEn(d.vinculo);
			}
			break;
		case ATRIBUTO:
			chequea(d.desigatributo.designador);
			if (d.desigatributo.designador != null && d.desigatributo.designador.dtipo0 != null) {
				if (d.desigatributo.designador.dtipo0.tipo == Tipo.REG) {
					DecVar c = d.desigatributo.designador.dtipo0.dtipo1.registro.listaCampos.consulta(d.id);
					if (c == null) {
						GestorErrores.error("El campo " + d.id
								+ " no existe en este struct", d);
					} else {
						t = c.dtipo0;
					}
				} else {
					GestorErrores.error("El designador debería ser un registro", d);
				}
			} else {
				t = null;
			}
			break;
		default:
			break;
		}

		d.dtipo0 = t;
	}

	private DTipo0 tipoEn(Declaracion vinculo) {
		return vinculo.dtipo0;
	}

	private boolean validoComoDesignador(Declaracion vinculo) {
		return (vinculo.clase == Clases.VARIABLE
				|| vinculo.clase == Clases.PARAMETRO);
	}

	// Expresiones

	private void chequea(Exp e) {
		DTipo0 t = new DTipo0();
		switch(e.tipoExp){
		case BASICO:
			switch(e.tipoResultante){
			case DESIG:
				GestorErrores.error("Un tipo básico no debería ser designador", e);
				break;
			case NUMENTERO:
				t.tipo = Tipo.INT;
				break;
			case NUMREAL:
				t.tipo = Tipo.REAL;
				break;
			case NULL:
				t.tipo = Tipo.PUNTERO;
				break;
			default:
				break;
			}
			break;
		case DESIGNADOR:
			chequea(e.designador);
			t = e.designador.dtipo0;
			break;
		case EXP1:
			chequea(e.exp11);
			t = e.exp11.dtipo0;
			break;
		case EXP2:
			chequea(e.exp2);
			t = e.exp2.dtipo0;
			break;
		case EXP3:
			chequea(e.exp3);
			t = e.exp3.dtipo0;
			break;
		case EXP4:
			chequea(e.exp4);
			t = e.exp4.dtipo0;
			break;
		case EXP0:
			chequea(e.exp0);
			t = e.exp0.dtipo0;
			break;			

		case EXP1EXP1:
			chequea(e.exp11);
			chequea(e.exp12);
			if(e.exp11.dtipo0 == null || e.exp12.dtipo0 == null){
				t = null;
			}
			else if (tiposComparables(e.exp11.dtipo0, e.exp12.dtipo0)){
				t.tipo = Tipo.INT;
			}
			else{
				GestorErrores.error("Tipos no comparables (opcomp)", e);
			}
			break;
		case EXP1EXP2:
			chequea(e.exp1);
			chequea(e.exp2);
			if(e.exp1.dtipo0 == null || e.exp2.dtipo0 == null){
				t = null;
			}
			else if (operacionAritmeticaValida(e.exp1.dtipo0, e.exp2.dtipo0)){
				t = e.exp2.dtipo0;
			}
			else{
				GestorErrores.error("Tipos no comparables (opmultiplicativo)", e);
			}
			break;
		case EXP2EXP3:
			chequea(e.exp22);
			chequea(e.exp3);
			if(e.exp22.dtipo0 == null || e.exp3.dtipo0 == null){
				t = null;
			}
			else if (operacionAritmeticaValida(e.exp22.dtipo0, e.exp3.dtipo0)){
				t = e.exp2.dtipo0;
			}
			else{
				GestorErrores.error("Tipos no comparables (opmultiplicativo)", e);
			}
			break;
		case OP3EXP3:
			switch(e.exp33.tipoResultante){
			case DESIG:
				GestorErrores.error("No se puede aplicar un operador unario a un designador", e);
				break;
			case NUMREAL:
				t.tipo = Tipo.REAL;
				break;
			case NUMENTERO:
				t.tipo = Tipo.INT;
				break;
			case NULL:
				GestorErrores.error("No se puede aplicar un operador unario a NULL", e);
				break;
			default:
				break;
			}	
			break;
		case OP3NAEXP4:
			switch(e.exp4.tipoResultante){
			case DESIG:
				GestorErrores.error("No se puede aplicar un operador unario a un designador", e);
				break;
			case NUMREAL:
				t.tipo = Tipo.REAL;
				break;
			case NUMENTERO:
				t.tipo = Tipo.INT;
				break;
			case NULL:
				GestorErrores.error("No se puede aplicar un operador unario a NULL", e);
				break;
			default:
				break;
			}	
			break;
		default:
			break;
		}
		e.dtipo0 = t;
	}

	private boolean operacionAritmeticaValida(DTipo0 t1, DTipo0 t2) {
		return compatibles(t1, t2);
	}

	private boolean tiposComparables(DTipo0 t1, DTipo0 t2) {
		return compatibles(t1, t2);
	}
}

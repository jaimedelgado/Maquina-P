package procesador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alex.AnalizadorLexicoTiny;
import ast.Cabecera;
import ast.Campo;
import ast.Campos;
import ast.Caso;
import ast.Casos;
import ast.DTipo0;
import ast.DTipo1;
import ast.DTipoObjeto;
import ast.DTipoPuntero;
import ast.DTipoRegistro;
import ast.DecFun;
import ast.DecTipo;
import ast.DecVar;
import ast.Declaracion;
import ast.Decs;
import ast.DesigArray;
import ast.DesigAtributo;
import ast.DesigPuntero;
import ast.Designador;
import ast.Exp;
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
import ast.LParamsInvoc;
import ast.Parametro;
import ast.Parametros;
import ast.ParamsInvoc;
import ast.ParteElse;
import ast.Programa;
import ast.Superclase;
public class Vinculador {

	private AnalizadorLexicoTiny alt;

	private int bloqueActual = -1;
	private List<HashMap<String, Declaracion>> tablasSimbolos;

	public Vinculador() {

	}

	private void iniciaTS() {
		tablasSimbolos = new ArrayList<HashMap<String, Declaracion>>();
	}

	private void abreBloque() {
		bloqueActual++;
		tablasSimbolos.add(new HashMap<String, Declaracion>());
	}

	private void cierraBloque() {
		tablasSimbolos.remove(bloqueActual);
		bloqueActual--;
	}

	private boolean insertaId(String id, Declaracion dec) {
		if (tablasSimbolos.get(bloqueActual).containsKey(id)) {
			return false;
		} else {
			tablasSimbolos.get(bloqueActual).put(id, dec);
			return true;
		}
	}

	private Declaracion declaracionDe(String id) {
		for (int i = tablasSimbolos.size() - 1; i >= 0; i--) {
			if (tablasSimbolos.get(i).containsKey(id)) {
				return tablasSimbolos.get(i).get(id);
			}
		}
		return null;
	}

	// Programa

	public void vincula(List<Declaracion> declaraciones,
			List<Instruccion> instrucciones) {
		iniciaTS();
		abreBloque();
		for (Declaracion d : declaraciones)
			vincula(d);
		/*for (Declaracion d : declaraciones)
			vinculaDefPunteros(d);
		*/
		if(instrucciones == null || instrucciones.size() == 0){
			GestorErrores.error("No se han podido encontrar instrucciones");
		}
		
		for (Instruccion i : instrucciones)
			vincula(i);
		cierraBloque();
	}

	// Declaraciones

	private void vincula(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			vincula(d.decFun);
			break;
		case TIPO:
			vincula(d.decT);
			break;
		case VARIABLE:
			vincula(d.decV);
			break;
		case PARAMETRO:
			vincula(d.dtipo0);
			break;
		default:
			break;
		}
	}

	private void vincula(DecVar d) {
		vincula(d.dtipo0);
		if (!insertaId(d.id, d)) {
			GestorErrores.error("Identificador de variable " + d.id
					+ " duplicado", d);
		}
	}

	private void vincula(DecTipo d) {
		vincula(d.dtipo0);
		if (!insertaId(d.id, d)) {
			GestorErrores.error("Identificador de tipo " + d.id + " duplicado", d);
		}
	}

	private void vincula(DecFun d) {
		if (!insertaId(d.cabecera.id, d)) {
			GestorErrores.error("Identificador de función " + d.id
					+ " duplicado", d);
		}

		abreBloque();
		insertaId(d.cabecera.id, d);

		/*List<Declaracion> declaraciones = d.getDeclaraciones();
		List<Declaracion> parametros = d.getParametros();
		List<Instruccion> instrucciones = d.iBloque.instrucciones
				.getInstrucciones();

		for (Declaracion param : parametros) {
			vincula(param.dtipo0);
			insertaId(param.id, param);
		}
		for (Declaracion dec : declaraciones)
			vincula(dec);
		for (Declaracion dec : declaraciones)
			vinculaDefPunteros(dec);
		for (Instruccion i : instrucciones)
			vincula(i);*/
		vincula(d.cabecera);
		vincula(d.programa);

		cierraBloque();
	}

	private void vincula(Programa programa) {
		vincula(programa.decs);
		vincula(programa.ins);
		
	}

	private void vincula(Decs decs) {
		vincula(decs.ldecs);
		
	}

	private void vincula(LDecs ldecs) {
		List<Declaracion> declaraciones = ldecs.getDeclaraciones();
		for (Declaracion dec : declaraciones)
			vincula(dec);
	}

	private void vincula(Cabecera cabecera) {
		vincula(cabecera.params);
		
	}

	private void vincula(Parametros params) {
		vincula(params.listaParametros);
		
	}

	private void vincula(LParametros listaParametros) {
		if(listaParametros!=null){
			List<Parametro> parametros = listaParametros.getParametros();
			for (Parametro p : parametros)
				vincula(p);
		}
		
	}

	private void vincula(Parametro p) {
		vincula(p.dtipo0);
		
		if (!insertaId(p.id, p)) {
			GestorErrores.error("Identificador de parametro " + p.id
					+ " duplicado", p);
		}
		
	}

	/*private void vinculaDefPunteros(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			vinculaDefPunteros((DecFun) d);
			break;
		case TIPO:
			vinculaDefPunteros((DecTipo) d);
			break;
		case VARIABLE:
			vinculaDefPunteros((DecVar) d);
			break;
		default:
			break;
		}
	}

	private void vinculaDefPunteros(DecVar d) {
		vinculaDefPunteros(d.dtipo0);
	}

	private void vinculaDefPunteros(DecTipo d) {
		vinculaDefPunteros(d.dtipo0);
	}

	private void vinculaDefPunteros(DecFun d) {
		for (Declaracion param : d.getParametros()) {
			vinculaDefPunteros(((Parametro) param).dtipo0);
		}
	}*/

	// Definiciones de tipo

	private void vincula(DTipo0 t) {
		switch (t.tipo) {
		case ARRAY:
			vincula(t.dtipo1);
			break;
		case REAL:
			vincula(t.dtipo1);
			break;
		case REFID:
			t.vinculo = declaracionDe(t.dtipo1.id);
			vincula(t.dtipo1);
			break;
		case INT:
			vincula(t.dtipo1);
			break;
		case PUNTERO:
			vincula(t.puntero);
			break;
		case REG:
			vincula(t.dtipo1);
			break;
		case OBJETO:
			vincula(t.dtipo1);
			break;
		default:
			break;
		}
	}

	private void vincula(DTipoPuntero puntero) {
		vincula(puntero.dtipo0);
		
	}

	private void vincula(DTipo1 t) {
		switch (t.tipo) {
		case ARRAY:
			vincula(t.tipoArray);
			break;
		case REAL:
			// No hacer nada
			break;
		case REFID:
			t.vinculo = declaracionDe(t.id);
			if (t.vinculo == null) {
				GestorErrores.error("Referencia a un tipo " + t.id
						+ " no declarado", t);
			}
			break;
		case INT:
			//no hacer nada
			break;
		case REG:
			vincula(t.registro);
			break;
		case OBJETO:
			vincula(t.objeto);
			break;
		default:
			break;
		}
		
	}

	private void vincula(DTipoObjeto objeto) {
		abreBloque();
		vincula(objeto.superclase);
		vincula(objeto.lcamposomet);
		cierraBloque();
	}

	private void vincula(Campos campos) {
		for (Campo c : campos.getCampos()) {
			vincula(c);
		}
		
	}

	private void vincula(Superclase superclase) {
		if(superclase.id!=null){
			superclase.vinculo = declaracionDe(superclase.id);
			if(superclase.vinculo==null){
				GestorErrores.error("Superclase " + superclase.id
						+ " no declarado", superclase);
			}
		}
	}

	private void vincula(DTipoRegistro registro) {
		abreBloque();
		vincula(registro.listaCampos);
		cierraBloque();
	}

	private void vincula(LCampos listaCampos) {
		List<DecVar> campos = listaCampos.getCampos();
		for (DecVar c : campos)
			vincula(c);
		
	}

	private void vincula(Campo c) {
		if(c.decV!=null){ vincula(c.decV);}
		if(c.decMet!=null){ vincula(c.decMet);}
	}

	

	// Procesamiento de las instrucciones

	private void vincula(Instruccion i) {
		switch (i.tipo) {
		case ASIG:
			vincula(i.iasig);
			break;
		case WHILE:
			vincula(i.iwhile);
			break;
		case IF:
			vincula(i.iif);
			break;
		case FREE:
			vincula(i.ifree);
			break;
		case INVOCACIONFUNMET:
			vincula(i.invocacionfunmet);
			break;
		case ALLOC:
			vincula(i.ialloc);
			break;
		case IN:
			vincula(i.iin);
			break;
		case OUT:
			vincula(i.iout);
			break;
		case RETURN:
			vincula(i.ireturn);
			break;
		default:
			break;
		}
	}

	private void vincula(IReturn ireturn) {
		vincula(ireturn.exp0);
		
	}

	private void vincula(IIn iin) {
		vincula(iin.designador);
	}

	private void vincula(IOut iout) {
		vincula(iout.designador);
	}

	private void vincula(IAlloc ialloc) {
		vincula(ialloc.designador);
	}

	private void vincula(InvocacionFunMet invocacion) {

		invocacion.designador.vinculo = declaracionDe(invocacion.designador.id);
		if (invocacion.designador.vinculo == null) {
			GestorErrores.error("Identificador de procedimiento " + invocacion.designador.id
					+ " no declarado", invocacion);
		}
		vincula(invocacion.paramsinvoc);
	
	}

	private void vincula(ParamsInvoc paramsinvoc) {
		vincula(paramsinvoc.listaParametros);
	}

	private void vincula(LParamsInvoc listaParametros) {
		if(listaParametros!=null){
			for (Exp0 e : listaParametros.getExp0s()) {
				
				vincula(e);
				
			}
		}
	}

	private void vincula(IFree idelete) {
		vincula(idelete.designador);
	}

	private void vincula(IIf icond) {
		vincula(icond.casos);
		vincula(icond.parteelse);
	}

	private void vincula(ParteElse parteelse) {
		if(parteelse!=null&&parteelse.is!=null){
			vincula(parteelse.is);
		}
		
	}

	private void vincula(Casos casos) {
		for (Caso c : casos.getCasos()) {
			vincula(c.exp0);
			vincula(c.is);
		}
	}

	private void vincula(Is is) {
		vincula(is.is);
		
	}

	private void vincula(IWhile iwhile) {
		vincula(iwhile.exp0);
		vincula(iwhile.ins);
	}

	private void vincula(Instrucciones ibloque) {
		if(ibloque!=null){
			for (Instruccion i : ibloque.getInstrucciones()) {
				vincula(i);
			}
		}
	}

	private void vincula(IAsig iasig) {
		vincula(iasig.designador);
		vincula(iasig.exp0);
	}

	// Procesamiento de los designadores

	private void vincula(Designador designador) {
		switch (designador.tipo) {
		case ARRAY:
			vincula(designador.desigarray);
			break;
		case THIS:
			vincula(designador.otrodesignador);
			break;
		case SUPER:
			vincula(designador.otrodesignador);
			break;
		case ID:
			designador.vinculo = declaracionDe(designador.id);
			if (designador.vinculo == null) {
				GestorErrores.error("Identificador " + designador.id
						+ " no declarado", designador);
			}
			break;
		case ATRIBUTO:
			vincula(designador.desigatributo);
			break;
		case PUNTERO:
			vincula(designador.desigpuntero);
			break;
		default:
			break;
		}
	}

	// Procesamiento de las Expresiones

	private void vincula(DesigPuntero desigpuntero) {
		vincula(desigpuntero.designador);
		
		
	}

	private void vincula(DesigAtributo desigatributo) {
		vincula(desigatributo.designador);
		desigatributo.designador.vinculo = declaracionDe(desigatributo.id);
		if(desigatributo.designador.vinculo==null){
			GestorErrores.error("Designador de atributo " + desigatributo.id
					+ " no declarado", desigatributo);
		}
		
	}

	private void vincula(DesigArray desigarray) {
		vincula(desigarray.designador);
		vincula(desigarray.exp0);
	}

	private void vincula(Exp e) {
		//OP0NA, EXP1EXP1, EXP1, OP1, EXP1EXP2, EXP2, OP2, EXP2EXP3, EXP3, OP3, OP2EXP3, OP3NAEXP3, EXP4, OP3NA, BASICO, DESIGNADOR, EXP0, FUNMET
		
		switch (e.tipoExp) {
		case BASICO:
			// Do nothing
			break;
		case DESIGNADOR:
			vincula(e.designador);
			break;
		case EXP1EXP1:
			vincula(e.exp11);
			vincula(e.exp12);
			break;
		case EXP1:
			vincula(e.exp11);
			break;
		case EXP1EXP2:
			vincula(e.exp1);
			vincula(e.exp2);
			break;
		case EXP2:
			vincula(e.exp2);
			break;
		case EXP2EXP3:
			vincula(e.exp22);
			vincula(e.exp3);
			break;
		case EXP3:
			vincula(e.exp3);
			break;
		case OP3EXP3:
			vincula(e.exp33);
			break;
		case OP3NAEXP4:
			vincula(e.exp4);
			break;
		case EXP4:
			vincula(e.exp4);
			break;
		case FUNMET:
			vincula(e.invocFunMet);
			break;
		case EXP0:
			vincula(e.exp0);
			break;
		default:
			break;
		}
	}
}

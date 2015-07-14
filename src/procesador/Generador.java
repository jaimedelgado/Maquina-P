package procesador;

import java.util.List;

import codigosconcretos.CodigoOp1;
import codigosconcretos.CodigoBasico;
import codigosconcretos.CodigoOp0NA;
import codigosconcretos.CodigoMemoria;
import codigosconcretos.CodigoOp2;
import codigosconcretos.CodigoOp3;
import codigosconcretos.CodigoOp3NA;
import ast.Campo;
import ast.Caso;
import ast.Casos;
import ast.DTipoObjeto;
import ast.DecFun;
import ast.DecMet;
import ast.Declaracion;
import ast.Decs;
import ast.Designador;
import ast.Exp;
import ast.Exp0;
import ast.Instruccion;
import ast.Instrucciones;
import ast.Parametro;
import ast.DTipo0.Tipo;
import ast.Parametro.Tipos;
import ast.ParteElse;
import ast.Programa;

public class Generador {

	public static String entryPoint = "#JUMP_TO_ENTRY_POINT#";

	
	public void genera(Programa programa, Decs declaraciones, Instrucciones instrucciones) {
		codigo(programa, declaraciones, instrucciones);
	}

	private static int cuentaInstrucciones(String cod) {
		return cod.split(Programa.SEPARATOR).length;
	}

	// Programa
	
	public static void codigo(Programa programa, Decs declaraciones, Instrucciones instrucciones) {
		programa.cod = codigoActivacionPrograma(programa);
		Programa.cinst = numeroInstruccionesActivacionPrograma(programa);
		
		for (Declaracion declaracion : declaraciones.ldecs.getDeclaraciones()) {
			codigo(declaracion);
			programa.cod += declaracion.cod;
		}
		
		for (Instruccion instruccion : instrucciones.getInstrucciones()) {
			codigo(instruccion);
			programa.cod += instruccion.cod;
		}
		programa.cod = programa.cod.replace(entryPoint,
				"ir_a " + instrucciones.getInstrucciones().get(0).comienzo);
	}

	public static int numeroInstruccionesActivacionPrograma(Programa programa) {
		return cuentaInstrucciones(programa.cod);
	}

	public static String codigoActivacionPrograma(Programa programa) {
		return "alloc 200" + Programa.SEPARATOR + "desapila"
				+ Programa.SEPARATOR + "apila " + programa.finDatos
				+ Programa.SEPARATOR + "desapila_dir 0" + Programa.SEPARATOR
				+ entryPoint + Programa.SEPARATOR;
	}

	// Declaraciones

	public static void codigo(Declaracion d) {
		switch (d.clase) {
		case FUNCION:
			codigo((DecFun) d.decFun);
			break;
		case TIPO:
			break;
		case VARIABLE:
			if(d.dtipo0.tipo==Tipo.OBJETO){
				codigo(d.dtipo0.dtipo1.objeto);
			}
			break;
		case PARAMETRO:
			break;
		/*case METODO:
			codigo((DecMet) d);							//Ya veremos
			break;*/
		default:
			break;

		}
	}

	private static void codigo(DTipoObjeto objeto) {
		for(Campo c : objeto.lcamposomet.getCampos()){
			codigo(c);
		}
		
	}

	private static void codigo(Campo c) {
		if(c.decMet!=null){
			codigo(c.decMet);
		}else if(c.decV!=null){
			codigo(c.decV);
		}
		
	}
	public static void codigoDecMet(DecMet d) {
		for (Declaracion declaracion : d.programa.decs.ldecs.getDeclaraciones()) {
			codigo(declaracion);
			d.cod = d.cod + declaracion.cod;
		}
		d.dirComienzo = Programa.cinst + 1;
		Programa.cinst += numeroInstruccionesPrologoDecMet(d);
		d.cod = d.cod + codigoPrologoDecMet(d);
		
		for (Instruccion instruccion : d.programa.ins.getInstrucciones()) {
			codigo(instruccion);
			d.cod += instruccion.cod;
		}
		d.cod += codigoEpilogoDecMet(d);
		Programa.cinst += numeroInstruccionEpilogoDecMet(d);
	}
	public static void codigo(DecFun d) {
		for (Declaracion declaracion : d.programa.decs.ldecs.getDeclaraciones()) {
			codigo(declaracion);
			d.cod = d.cod + declaracion.cod;
		}
		d.dirComienzo = Programa.cinst + 1;
		Programa.cinst += numeroInstruccionesPrologo(d);
		d.cod = d.cod + codigoPrologo(d);
		
		for (Instruccion instruccion : d.programa.ins.getInstrucciones()) {
			codigo(instruccion);
			d.cod += instruccion.cod;
		}
		d.cod += codigoEpilogo(d);
		Programa.cinst += numeroInstruccionEpilogo(d);
	}
	
	public static String codigoEpilogo(DecFun d) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila "
				+ (2 + d.tamParamMasVariables) + Programa.SEPARATOR + "resta"
				+ Programa.SEPARATOR + "desapila_dir 0" + Programa.SEPARATOR
				+ "apila_dir 0" + Programa.SEPARATOR + "apila 2"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_ind" + Programa.SEPARATOR + "desapila_dir " + d.nivel
				+ Programa.SEPARATOR + "apila_dir 0" + Programa.SEPARATOR
				+ "apila 1" + Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_ind" + Programa.SEPARATOR + "ir_ind"
				+ Programa.SEPARATOR;
	}
	public static String codigoEpilogoDecMet(DecMet d) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila "
				+ (2 + d.tamParamMasVariables) + Programa.SEPARATOR + "resta"
				+ Programa.SEPARATOR + "desapila_dir 0" + Programa.SEPARATOR
				+ "apila_dir 0" + Programa.SEPARATOR + "apila 2"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_ind" + Programa.SEPARATOR + "desapila_dir " + d.nivel
				+ Programa.SEPARATOR + "apila_dir 0" + Programa.SEPARATOR
				+ "apila 1" + Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_ind" + Programa.SEPARATOR + "ir_ind"
				+ Programa.SEPARATOR;
	}

	public static String codigoPrologo(DecFun d) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila 2"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_dir " + d.nivel + Programa.SEPARATOR + "desapila_ind"
				+ Programa.SEPARATOR + "desapila_dir " + d.nivel
				+ Programa.SEPARATOR + "apila_dir 0" + Programa.SEPARATOR
				+ "apila " + (2 + d.tamParamMasVariables) + Programa.SEPARATOR
				+ "suma" + Programa.SEPARATOR + "desapila_dir 0"
				+ Programa.SEPARATOR;
	}
	public static String codigoPrologoDecMet(DecMet d) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila 2"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR
				+ "apila_dir " + d.nivel + Programa.SEPARATOR + "desapila_ind"
				+ Programa.SEPARATOR + "desapila_dir " + d.nivel
				+ Programa.SEPARATOR + "apila_dir 0" + Programa.SEPARATOR
				+ "apila " + (2 + d.tamParamMasVariables) + Programa.SEPARATOR
				+ "suma" + Programa.SEPARATOR + "desapila_dir 0"
				+ Programa.SEPARATOR;
	}
	public static int numeroInstruccionEpilogo(DecFun d) {
		return cuentaInstrucciones(codigoEpilogo(d));
	}
	public static int numeroInstruccionEpilogoDecMet(DecMet d) {
		return cuentaInstrucciones(codigoEpilogoDecMet(d));
	}
	public static int numeroInstruccionesPrologo(DecFun d) {
		return cuentaInstrucciones(codigoPrologo(d));
	}
	public static int numeroInstruccionesPrologoDecMet(DecMet d) {
		return cuentaInstrucciones(codigoPrologoDecMet(d));
	}

	// Instrucciones

	public static void codigo(Instrucciones is) {
		is.comienzo = Programa.cinst;
		for (Instruccion instr : is.getInstrucciones()) {
			codigo(instr);
			is.cod += instr.cod;
		}
		is.fin = Programa.cinst;
	}
	
	public static void codigo(Instruccion i) {
		switch (i.tipo) {
		case ASIG: {
			i.comienzo = Programa.cinst + 1;
			codigo(i.iasig.designador);
			codigo(i.iasig.exp0);
			i.cod = i.iasig.designador.cod + i.iasig.exp0.cod
					+ codigoFinAsig(i);
			Programa.cinst += numeroInstruccionesFinAsig(i);
			i.fin = Programa.cinst;
			break;
		}
		case WHILE: {
			i.comienzo = Programa.cinst-1;
			//i.cod += "ir_a " + (i.comienzo + 1) + Programa.SEPARATOR;
			
			int comienzo = Programa.cinst+1;
			Exp0 exp0 = i.iwhile.exp0;
			Programa.cinst++;
			codigo(exp0);
			
			String codigoInstrucciones="";
			for(Instruccion instruccion :i.iwhile.ins.is.getInstrucciones()){
				codigo(instruccion);
				codigoInstrucciones+=instruccion.cod;
				Programa.cinst++;
			}
			
			i.cod += exp0.cod;
			i.cod += "ir_f " + Programa.cinst + Programa.SEPARATOR;
			i.cod += codigoInstrucciones;
			i.cod += "ir_a " + comienzo + Programa.SEPARATOR;
			break;
		}
		case IF: {
			// 1:ir_a(3)
			// 2:ir_a(6)
			// 3:primer caso
			// ...
			// 4:ir_a(2)
			// 5:segundo caso
			// ...
			// ir_a(4)
			// 6:final
			String codCasos="", clave="CLAVE";
			i.comienzo = Programa.cinst + 1;
			// Primer caso
			//i.cod += "ir_a " + (i.comienzo + 2) + Programa.SEPARATOR;
			Programa.cinst += 2; // Los dos ir_a, aunque lo a�ada luego
			List<Caso> casos = i.iif.casos.getCasos();
			for (Caso c : casos) {
				c.comienzo = Programa.cinst + 1;

				codigo(c.exp0);

				Programa.cinst++; // Por el ir_f de la condicion

				for (Instruccion i2 : c.is.is.getInstrucciones()) {
					codigo(i2);
					c.is.cod += i2.cod;
				}

				c.fin = Programa.cinst;
				c.cod = c.exp0.cod;
				// Si no se cumple la Exp, ir a c.fin + 1
				c.cod += "ir_f " + (c.fin) + Programa.SEPARATOR;
				// Si se cumple, ejecutar todo el codigo
				c.cod += c.is.cod;
				// Si se ejecuta, al final saltamos a c.comienzo - 1
				c.cod += "ir_a " + clave + Programa.SEPARATOR;
				Programa.cinst++;
				codCasos += c.cod;
			}
			


			//Parte Else
			ParteElse pe = i.iif.parteelse;
			pe.comienzo = Programa.cinst + 1;
			
			if(pe.is!=null && pe.is.is!=null ){
				for (Instruccion it : pe.is.is.getInstrucciones()) {
					codigo(it);
					pe.is.cod += it.cod;
				}
				pe.fin = Programa.cinst + 1;

				// Si no se cumple la Exp, ir a c.fin + 1
				//pe.cod += "ir_f " + (pe.fin + 1) + Programa.SEPARATOR;
				// Si se cumple, ejecutar todo el código
				pe.cod += pe.is.cod;

				//pe.cod += "ir_a " + clave + Programa.SEPARATOR;
				// Si se ejecuta, al final saltamos a c.comienzo - 1
				//pe.cod += "ir_a " + (pe.comienzo - 1) + Programa.SEPARATOR;
				Programa.cinst++;
				codCasos+=pe.cod;
			}
			// Saltar al final
						//i.cod += "ir_a " + (Programa.cinst + 1) + Programa.SEPARATOR;
			// Saltar al final
			//i.cod += "ir_a " + (Programa.cinst + 1) + Programa.SEPARATOR;
			/*if(pe.is!=null){
				pe.cod +=pe.is.cod;
			}*/
			i.cod += codCasos.replaceAll(clave, String.valueOf(Programa.cinst));
			
			break;
		}
		case FREE: {
			i.comienzo = Programa.cinst + 1;
			codigo(i.ifree.designador);
			int tam = i.ifree.designador.vinculo.dtipo0.puntero.dtipo0.tam;
			i.cod = i.ifree.designador.cod + codigoFinDelete(i, tam);
			Programa.cinst += numeroInstruccionesFinDelete(i);
			i.fin = Programa.cinst;
			
			break;
		}
		case INVOCACIONFUNMET: {
			i.comienzo = Programa.cinst + 1;
			i.cod = codigoComienzoPaso(i);
			Programa.cinst += numeroInstruccionesComienzoPaso(i);
			if (i.invocacionfunmet.paramsinvoc.listaParametros != null) {
				int numArg = 0;
				for (Exp e : i.invocacionfunmet.paramsinvoc.listaParametros.getExp0s()) {
					i.cod += "dup" + Programa.SEPARATOR;
					Programa.cinst += 1;
					i.cod += codigoPaso(i, e, numArg);
					numArg++;
				}
			}
			i.cod += codigoFinLlamada(i);
			Programa.cinst += numeroInstruccionesFinLlamada(i);
			i.fin = Programa.cinst;
			break;
		}
		case ALLOC: {
			i.comienzo = Programa.cinst + 1;
			codigo(i.ialloc.designador);
			int tam = i.ialloc.designador.vinculo.dtipo0.puntero.dtipo0.tam;
			i.cod = i.ialloc.designador.cod + codigoFinAlloc(i, tam);
			Programa.cinst += numeroInstruccionesFinAlloc(i);
			i.fin = Programa.cinst;
			break;
		}
		case IN: {
			i.comienzo = Programa.cinst + 1;
			codigo(i.iin.designador);
			i.cod = i.iin.designador.cod + codigoFinIn(i);
			Programa.cinst += numeroInstruccionesFinIn(i);
			i.fin = Programa.cinst;
			break;
		}
		case OUT: {
			i.comienzo = Programa.cinst + 1;
			codigo(i.iout.designador);
			i.cod = i.iout.designador.cod + codigoFinOut(i);
			Programa.cinst += numeroInstruccionesFinOut(i);
			i.fin = Programa.cinst;

			break;
		}
		default:
			break;
		}

	}

	private static String codigoFinAlloc(Instruccion i, int tam) {
		return "alloc " + tam + Programa.SEPARATOR +
				"desapila_ind" + Programa.SEPARATOR;
	}

	public static String codigoFinIn(Instruccion i) {
		return "in" + Programa.SEPARATOR + "desapila_ind" + Programa.SEPARATOR;
	}

	public static int numeroInstruccionesComienzoPaso(Instruccion i) {
		return cuentaInstrucciones(codigoComienzoPaso(i));
	}

	public static String codigoComienzoPaso(Instruccion i) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila 3"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR;

	}

	public static String codigoFinLlamada(Instruccion i) {
		return "apila_dir 0" + Programa.SEPARATOR + "apila 1"
				+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR + "apila "
				+ (Programa.cinst + 7) + Programa.SEPARATOR + "desapila_ind"
				+ Programa.SEPARATOR + "ir_a "
				+ ((DecFun) i.invocacionfunmet.designador.vinculo).dirComienzo
				+ Programa.SEPARATOR;

	}

	public static int numeroInstruccionesFinLlamada(Instruccion i) {
		return cuentaInstrucciones(codigoFinLlamada(i));
	}

	public static boolean esDesignador(Exp e) {
		switch (e.tipoExp) {
		case BASICO:
			return false;
		case DESIGNADOR:
			return true;
		case EXP1:
			return esDesignador(e.exp11);
		case EXP2:
			return esDesignador(e.exp2);
		case EXP3:
			return esDesignador(e.exp3);
		case EXP4:
			return esDesignador(e.exp4);
		case EXP0:
			return esDesignador(e.exp0);
		case EXP1EXP1: 
			return false;
		case EXP1EXP2:
			return false;
		case EXP2EXP3:
			return false;
		case OP3EXP3:
			return false;
		case OP3NAEXP4:
			return false;
		default:
			return false;
		}
	}

	private static Designador getDesignador(Exp e) {
		switch (e.tipoExp) {
		case BASICO:
			return null;
		case DESIGNADOR:
			return e.designador;
		case EXP1:
			return getDesignador(e.exp11);
		case EXP2:
			return getDesignador(e.exp2);
		case EXP3:
			return getDesignador(e.exp3);
		case EXP4:
			return getDesignador(e.exp4);
		case EXP0:
			return getDesignador(e.exp0);
		case EXP1EXP1:
			return null;
		case EXP1EXP2:
			return null;
		case EXP2EXP3:
			return null;
		case OP3EXP3:
			return null;
		case OP3NAEXP4:
			return null;
		default:
			return null;
		}
	}

	public static String codigoPaso(Instruccion i, Exp e, int numArg) {
		DecFun sp = (DecFun) i.invocacionfunmet.designador.vinculo;
		Parametro param = sp.cabecera.params.listaParametros.getParametros().get(numArg);
		i.invocacionfunmet.numInstruccionesPaso = 0;
		String cod = "apila " + param.dir + Programa.SEPARATOR + "suma" + Programa.SEPARATOR;
		Programa.cinst += 2;
		if (esDesignador(e)) {
			Designador d = getDesignador(e);
			codigo(d);
			cod += d.cod;
			if (param.tipoParametro == Tipos.VALOR) {
				cod += "clona " + d.dtipo0.tam + Programa.SEPARATOR;
				Programa.cinst += 1;
			} else { // VARIABLE
				cod += "desapila_ind" + Programa.SEPARATOR;
				Programa.cinst += 1;
			}
		} else {
			codigo(e);
			cod += e.cod + "desapila_ind" + Programa.SEPARATOR;
			Programa.cinst += 1;
		}

		return cod;
	}

	public static String codigoFinOut(Instruccion i) {
		return "apila_ind" + Programa.SEPARATOR +"out" + Programa.SEPARATOR;
	}

	public static int numeroInstruccionesFinOut(Instruccion i) {
		return cuentaInstrucciones(codigoFinOut(i));
	}

	public static int numeroInstruccionesFinIn(Instruccion i) {
		return cuentaInstrucciones(codigoFinIn(i));
	}

	public static int numeroInstruccionesFinAlloc(Instruccion i) {
		return 2;
	}

	public static String codigoFinDelete(Instruccion i, int tam) {
		return "free " + tam + Programa.SEPARATOR;
	}

	public static int numeroInstruccionesFinDelete(Instruccion i) {
		return 1;
	}

	public static String codigoFinAsig(Instruccion i) {
		return "desapila_ind" + Programa.SEPARATOR;
	}

	public static int numeroInstruccionesFinAsig(Instruccion i) {
		return cuentaInstrucciones(codigoFinAsig(i));
	}

	public static void codigo(Caso c) {
		c.comienzo = Programa.cinst + 1;
		codigo(c.exp0);
		Programa.cinst++; // Por el ir_f de la condicion
	}

	// Designadores

	public static void codigo(Designador d) {
		if (!d.cod.equals("")) {
			return;
		}
		switch (d.tipo) {
		case ARRAY:
			codigo(d.desigarray.designador);
			codigo(d.desigarray.exp0);
			Programa.cinst += numeroInstruccionesIndexacion(d);
			d.cod += d.desigarray.designador.cod + d.desigarray.exp0.cod + codigoIndexacion(d);
			break;
		case PUNTERO:
			codigo(d.desigpuntero.designador);
			d.cod = d.desigpuntero.designador + codigoDereferencia(d);
			Programa.cinst += numeroInstruccionesDereferencia(d);
			break;
		case ID:
			d.cod = codigoAccesoId(d);
			Programa.cinst += numeroInstruccionesAccesoId(d);
			break;
		case ATRIBUTO:
			codigo(d.desigatributo.designador);
			d.cod += d.desigatributo.designador.cod;
			d.cod += "apila "
					+ d.desigatributo.designador.dtipo0.dtipo1.registro.listaCampos.consulta(d.desigatributo.id).desp
					+ Programa.SEPARATOR;
			d.cod += "suma" + Programa.SEPARATOR;

			Programa.cinst += numeroInstruccionesAccesoCampo(d);
			break;
		//This
		//Super	
		default:
			break;
		}
	}

	public static int numeroInstruccionesAccesoCampo(Designador d) {
		return 2;
	}

	public static int numeroInstruccionesAccesoId(Designador d) {
		return cuentaInstrucciones(d.cod);
	}

	public static String codigoAccesoId(Designador des) {
		String cod = "";
		switch(des.tipo){
		case ARRAY:
			cod = codigoAccesoId(des.desigarray.designador);
			codigo(des.desigarray.exp0);
			Programa.cinst -= cuentaInstrucciones(des.desigarray.exp0.cod);
			cod += des.desigarray.exp0.cod;
			cod += "apila " + des.desigarray.designador.dtipo0.dtipo1.tipoArray.tam + Programa.SEPARATOR;
			cod += "multiplica" + Programa.SEPARATOR;
			cod += "suma" + Programa.SEPARATOR;
			des.cod = cod;
			return cod;
		case PUNTERO:
			cod = codigoAccesoId(des.desigpuntero.designador);
			cod += "apila_ind" + Programa.SEPARATOR;
			des.cod = cod;
			return cod;
		case ID:
			Declaracion vinculo = null;
			
			Designador designador = des;
			
			
			vinculo = designador.vinculo;
	
			
			int n = vinculo.nivel;
			int d = vinculo.dir;
			
			if (n == 0) {
				cod += "apila " + d +  Programa.SEPARATOR;
				des.cod = cod;
				return cod;
			} else {
				cod += "apila_dir " + n + Programa.SEPARATOR + "apila " + d
						+ Programa.SEPARATOR + "suma" + Programa.SEPARATOR;
	
				if (vinculo instanceof Parametro
						&& ((Parametro) vinculo).tipoParametro == Tipos.VARIABLE) {
					cod += "apila_ind" + Programa.SEPARATOR;
				}
	
				des.cod = cod;
				return cod;
			}
		case ATRIBUTO:
			cod = codigoAccesoId(des.desigatributo.designador);
			
			int desp = des.desigatributo.designador.dtipo0.dtipo1.registro.listaCampos.consulta(des.desigatributo.id).desp;
			
			cod += "apila " + desp + Programa.SEPARATOR;
			cod += "suma" + Programa.SEPARATOR;
			
			des.cod = cod;
			return cod;
		default:
			des.cod = "";
			return "";
		}
	}

	public static String codigoDereferencia(Designador d) {
		return "apila_ind" + Programa.SEPARATOR;
	}

	public static int numeroInstruccionesDereferencia(Designador d) {
		return 1;
	}

	public static int numeroInstruccionesIndexacion(Designador d) {
		return 3;
	}

	public static String codigoIndexacion(Designador d) {
		return "apila " + d.desigarray.designador.dtipo0.dtipo1.tipoArray.tam + Programa.SEPARATOR
				+ "multiplica" + Programa.SEPARATOR + "suma"
				+ Programa.SEPARATOR;
	}

	// Instrucciones

	public static void codigo(Exp e) {
		switch (e.tipoExp) {
		case BASICO:
			CodigoBasico.codigoBasico(e);
			break;
		case DESIGNADOR:
			CodigoMemoria.codigoDesignador(e);
			break;
		case EXP1:					//EXP1
			codigo(e.exp11);
			e.cod = e.exp11.cod;
			break;
		case EXP2:
			codigo(e.exp2);
			e.cod = e.exp2.cod;
			break;
		case EXP3:
			codigo(e.exp3);
			e.cod = e.exp3.cod;
			break;
		case EXP4:
			codigo(e.exp4);
			e.cod = e.exp4.cod;
			break;
		case EXP0:
			codigo(e.exp0);
			e.cod = e.exp0.cod;
			break;
		case EXP1EXP2:						//EXP1EXP1
			CodigoOp1.codigoAditivo(e);
			break;
		case EXP1EXP1:
			CodigoOp0NA.codigoComp(e);
			break;
		case EXP2EXP3:
			CodigoOp2.codigoMult(e);
			break;
		case OP3EXP3:
			CodigoOp3.codigoUnario(e);
			break;
		case OP3NAEXP4:
			CodigoOp3NA.codigoCast(e);
			break;
		default:
			break;
		}
	}
}

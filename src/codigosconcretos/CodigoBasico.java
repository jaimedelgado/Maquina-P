package codigosconcretos;

import procesador.GestorErrores;
import ast.Exp;
import ast.Programa;

public class CodigoBasico {
	public static void codigoBasico(Exp e) {
		switch (e.tipoResultante) {
		case DESIG:
			GestorErrores.error("Tipo básico no puede ser designador", e);
			break;
		case NUMENTERO:
			codigoInt(e);
			Programa.cinst++;
			break;
		case NUMREAL:
			codigoReal(e);
			Programa.cinst++;
			break;
		case NULL:
			codigoNull(e);
			Programa.cinst++;
			break;
		case INVOC:
			codigoInvocacion(e);
			Programa.cinst++;
			break;
		default:
			break;
		}
	}

	private static void codigoNull(Exp e) {
		e.cod = "apila 0" + Programa.SEPARATOR;
	}

	private static void codigoReal(Exp e) {
		e.cod = "apila " + e.numeroReal + Programa.SEPARATOR;
	}

	private static void codigoInt(Exp e) {
		e.cod = "apila " + e.numeroEntero + Programa.SEPARATOR;
	}
	
	private static void codigoInvocacion(Exp e) {
		e.cod = "apila " + e.invocFunMet + Programa.SEPARATOR;
	}

}

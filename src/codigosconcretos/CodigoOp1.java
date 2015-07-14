package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoOp1 {
	public static void codigoAditivo(Exp e) {
		switch (e.op1.tipo) {
		case MAS:
			codigoMas(e);
			break;
		case MENOS:
			codigoMenos(e);
			break;
		case OR:
			codigoOr(e);
			break;
		default:
			break;
		}
	}

	private static void codigoOr(Exp e) {
		Generador.codigo(e.exp1);
		Generador.codigo(e.exp2);
		e.cod = e.exp1.cod + e.exp2.cod + "or" + Programa.SEPARATOR;
		Programa.cinst++;
	}

	private static void codigoMenos(Exp e) {
		Generador.codigo(e.exp1);
		Generador.codigo(e.exp2);
		e.cod = e.exp1.cod + e.exp2.cod + "resta" + Programa.SEPARATOR;
		Programa.cinst++;
	}

	private static void codigoMas(Exp e) {
		Generador.codigo(e.exp1);
		Generador.codigo(e.exp2);
		e.cod = e.exp1.cod + e.exp2.cod + "suma" + Programa.SEPARATOR;
		Programa.cinst++;
	}
}

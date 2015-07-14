package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoOp0NA {
	
	public static void codigoComp(Exp e) {
		switch (e.op0na.tipo) {
		case EQ:
			codigoEq(e);
			break;
		case GE:
			codigoGe(e);
			break;
		case GT:
			codigoGt(e);
			break;
		case LE:
			codigoLe(e);
			break;
		case LT:
			codigoLt(e);
			break;
		case NEQ:
			codigoNeq(e);
			break;
		default:
			break;
		}
	}

	private static void codigoEq(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "igual" + Programa.SEPARATOR;
		Programa.cinst++;
	}

	private static void codigoGe(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "mayor_o_igual" + Programa.SEPARATOR;
		Programa.cinst++;		
	}

	private static void codigoGt(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "mayor" + Programa.SEPARATOR;
		Programa.cinst++;		
	}

	private static void codigoLe(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "menor_o_igual" + Programa.SEPARATOR;
		Programa.cinst++;		
	}

	private static void codigoLt(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "menor" + Programa.SEPARATOR;
		Programa.cinst++;
	}

	private static void codigoNeq(Exp e) {
		Generador.codigo(e.exp11);
		Generador.codigo(e.exp12);
		e.cod = e.exp11.cod + e.exp12.cod + "distinto" + Programa.SEPARATOR;
		Programa.cinst++;		
	}
}

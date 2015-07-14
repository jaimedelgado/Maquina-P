package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoOp3NA {
	public static void codigoCast(Exp e) {
		switch (e.op3na.tipo) {
		case REAL:
			Generador.codigo(e.exp4);
			e.cod = e.exp4.cod + "a_real" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		case INT:
			Generador.codigo(e.exp4);
			e.cod = e.exp4.cod + "a_entero" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		default:
			break;
		}
	}
}

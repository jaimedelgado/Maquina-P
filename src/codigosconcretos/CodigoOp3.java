package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoOp3 {
	public static void codigoUnario(Exp e) {
		switch (e.op3.tipo) {
		case MENOS:
			Generador.codigo(e.exp33);
			e.cod = e.exp33.cod + "menos_unario" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		case NOT:
			Generador.codigo(e.exp33);
			e.cod = e.exp33.cod + "not" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		default:
			break;
		}
	}
}

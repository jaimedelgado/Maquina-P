package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoOp2 {

	public static void codigoMult(Exp e) {
		switch(e.op2.tipo){
		case AND:
			Generador.codigo(e.exp22);
			Generador.codigo(e.exp3);
			e.cod = e.exp22.cod + e.exp3.cod + "and" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		case DIV:
			Generador.codigo(e.exp22);
			Generador.codigo(e.exp3);
			e.cod = e.exp22.cod + e.exp3.cod + "divide" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		case MOD:
			Generador.codigo(e.exp22);
			Generador.codigo(e.exp3);
			e.cod = e.exp22.cod + e.exp3.cod + "modulo" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		case POR:
			Generador.codigo(e.exp22);
			Generador.codigo(e.exp3);
			e.cod = e.exp22.cod + e.exp3.cod + "multiplica" + Programa.SEPARATOR;
			Programa.cinst++;
			break;
		default:
			break;
		}
	}

}

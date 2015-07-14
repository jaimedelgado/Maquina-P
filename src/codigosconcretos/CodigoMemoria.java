package codigosconcretos;

import procesador.Generador;
import ast.Exp;
import ast.Programa;

public class CodigoMemoria {
	public static void codigoDesignador(Exp e) {
		if (!e.cod.equals("")) {
			return;
		}
		String cod = Generador.codigoAccesoId(e.designador);
		Programa.cinst += Generador.numeroInstruccionesAccesoId(e.designador);
		cod += "apila_ind" + Programa.SEPARATOR;
		Programa.cinst++;
		e.cod = cod;
	}
}

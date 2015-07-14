package procesador;

import ast.Nodo;

public class GestorErrores {
	
	public static void error(String message, Nodo contexto){
		System.err.println("Error (linea " + contexto.fila + "): " + message);
		System.exit(-1);
	}

	public static void error(String message) {
		System.err.println("Error: " + message);
		System.exit(-1);
	}
}

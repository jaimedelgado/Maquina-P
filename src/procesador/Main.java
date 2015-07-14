package procesador;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import ast.Programa;
import astCUP.AnalizadorSintactico;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintactico asint = new AnalizadorSintactico(alex);
	 Programa p = (Programa)asint.parse().value;
	 Procesador procesador = new Procesador(alex);
	 String result = procesador.procesa(p);
	 System.out.println(result);
 }
}   
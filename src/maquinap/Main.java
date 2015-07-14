package maquinap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import procesador.Procesador;
import alex.AnalizadorLexicoTiny;
import ast.Programa;
import astCUP.AnalizadorSintactico;
import maquinap.exceptions.ExecutionErrorException;
import maquinap.exceptions.InstructionUnimplementedException;

public class Main {
	
	public static void main(String[] args) throws Exception{
	
		BufferedReader br=null;
		MaquinaP maquina = null;
		try {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			 AnalizadorSintactico asint = new AnalizadorSintactico(alex);
			 Programa p = (Programa)asint.parse().value;
			 Procesador procesador = new Procesador(alex);
			 String result = procesador.procesa(p);
			 File f = new File("codigop.txt");
			 PrintWriter fw = new PrintWriter(f);
			 fw.write(result);
			 fw.close();
			 
			 br = new BufferedReader(new FileReader("codigop.txt"));
			maquina = new MaquinaP(br);
		} catch (IOException e1) {
			System.err.println("Could not read buffer: " + e1);
			System.exit(3);
		}
		
		try {
			maquina.run();
			System.out.println("Run completed succesfully");
		} catch (InstructionUnimplementedException | ExecutionErrorException e) {
			System.err.println("Error running instruction: " + e);
			System.exit(4);
		}
		
		try {
			if(br != null){
				br.close();
			}
		} catch (IOException e) {
			System.err.println("Error closing the input file");
			System.exit(5);
		}
		
		System.exit(0);
	}
}

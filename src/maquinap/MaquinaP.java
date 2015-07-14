package maquinap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import maquinap.exceptions.ExecutionErrorException;
import maquinap.exceptions.InstructionUnimplementedException;
import maquinap.instrucciones.Instruccion;
import maquinap.instrucciones.Nop;
import maquinap.instrucciones.aritmeticas.AEntero;
import maquinap.instrucciones.aritmeticas.AReal;
import maquinap.instrucciones.aritmeticas.Divide;
import maquinap.instrucciones.aritmeticas.MenosUnario;
import maquinap.instrucciones.aritmeticas.Modulo;
import maquinap.instrucciones.aritmeticas.Multiplica;
import maquinap.instrucciones.aritmeticas.Resta;
import maquinap.instrucciones.aritmeticas.Suma;
import maquinap.instrucciones.booleanas.And;
import maquinap.instrucciones.booleanas.Distinto;
import maquinap.instrucciones.booleanas.Igual;
import maquinap.instrucciones.booleanas.Mayor;
import maquinap.instrucciones.booleanas.MayorOIgual;
import maquinap.instrucciones.booleanas.Menor;
import maquinap.instrucciones.booleanas.MenorOIgual;
import maquinap.instrucciones.booleanas.Not;
import maquinap.instrucciones.entradasalida.Escribe;
import maquinap.instrucciones.entradasalida.Lee;
import maquinap.instrucciones.memoria.Alloc;
import maquinap.instrucciones.memoria.Apila;
import maquinap.instrucciones.memoria.Apila_dir;
import maquinap.instrucciones.memoria.Apila_ind;
import maquinap.instrucciones.memoria.Clona;
import maquinap.instrucciones.memoria.Desapila;
import maquinap.instrucciones.memoria.Desapila_dir;
import maquinap.instrucciones.memoria.Desapila_ind;
import maquinap.instrucciones.memoria.Dup;
import maquinap.instrucciones.memoria.Free;
import maquinap.instrucciones.saltos.Ir_a;
import maquinap.instrucciones.saltos.Ir_f;
import maquinap.instrucciones.saltos.Ir_ind;
import maquinap.valores.Valor;

public class MaquinaP {

	private int PC;
	
	private ArrayList<Instruccion> instructions;
	private Stack<Valor> valorStack = new Stack<Valor>();
	private MemoriaDinamica memoriaDinamica;
	public MaquinaP(BufferedReader br) throws IOException {
		
		
		instructions = new ArrayList<>();
		memoriaDinamica = new MemoriaDinamica();

		String line;
		while ((line = br.readLine()) != null) {
			
			Instruccion instr = parse(line);
			if (instr == null) {
				System.err
						.println("Instruction could not be recognized: \""
								+ line + "\"");
				System.exit(6);
			}
			instructions.add(instr);				
		}
		
		PC = 1;
	}
	
	public MemoriaDinamica getMemoriaDinamica(){
		return memoriaDinamica;
	}
	
	public Set<Class<? extends Instruccion>> allInstructions(){
		Set<Class<? extends Instruccion>> instrucciones = new HashSet<>();
		
		instrucciones.add(Divide.class);
		instrucciones.add(Multiplica.class);
		instrucciones.add(Suma.class);
		instrucciones.add(Resta.class);
		instrucciones.add(MenosUnario.class);
		instrucciones.add(Distinto.class);
		instrucciones.add(Igual.class);
		instrucciones.add(Mayor.class);
		instrucciones.add(Menor.class);
		instrucciones.add(MenorOIgual.class);
		instrucciones.add(MayorOIgual.class);
		instrucciones.add(Not.class);
		instrucciones.add(Escribe.class);
		instrucciones.add(Lee.class);
		instrucciones.add(Alloc.class);
		instrucciones.add(Apila_dir.class);
		instrucciones.add(Apila_ind.class);
		instrucciones.add(Apila.class);
		instrucciones.add(Clona.class);
		instrucciones.add(Desapila_dir.class);
		instrucciones.add(Desapila_ind.class);
		instrucciones.add(Desapila.class);
		instrucciones.add(Dup.class);
		instrucciones.add(Free.class);
		instrucciones.add(Ir_a.class);
		instrucciones.add(Ir_f.class);
		instrucciones.add(Nop.class);
		instrucciones.add(Ir_ind.class);
		instrucciones.add(And.class);
		instrucciones.add(Modulo.class);
		instrucciones.add(AReal.class);
		instrucciones.add(AEntero.class);
		
		return instrucciones;
	}

	public Instruccion parse(String line) {

		Set<Class<? extends Instruccion>> instructions = allInstructions();

		Iterator<Class<? extends Instruccion>> it = instructions.iterator();
		while (it.hasNext()) {
			Class<?> instr = it.next();
			try {
				Instruccion i = (Instruccion) instr.getConstructor()
						.newInstance();
				
				// Ignore line comments
				if(line.contains("//")){ 
					line = line.substring(0, line.indexOf("//"));
				}
				
				if(line.startsWith("[") && line.contains("]")){
					MaquinaP.TagManager.getInstance().addTag(line.split(" ")[0], MaquinaP.TagManager.getInstance().getInstr());
					line = line.substring(line.indexOf("]") + 1, line.length());
				}
				
				if (i.tryParse(line)) {
					MaquinaP.TagManager.getInstance().nextInstr();
					return i;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return null;
	}
	
	public void nextInstr() throws InstructionUnimplementedException, ExecutionErrorException{
		System.out.println(instructions.get(PC - 1).toString());
		instructions.get(PC - 1).ejecuta(this);
	}
	
	public void increasePC(){
		PC++;
	}
	
	public void setPC(int newPC){
		PC = newPC;
	}

	public void push(Valor valor) {
		valorStack.push(valor);
	}

	public Valor pop() {
		try{
			return valorStack.pop();
		}
		catch (NullPointerException e){
			System.err.println(e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void printStack(){
		Stack<Valor> newStack = (Stack<Valor>)valorStack.clone();
		System.out.println("++++++++++++++++++++");
		while(!newStack.isEmpty()){
			Valor value = newStack.pop();
			if(value == null){
				System.out.println("NULL");
			}
			else{
				System.out.println(value.toString());
			}
		}
		System.out.println("++++++++++++++++++++\n\n");
		
	}

	public void run() throws InstructionUnimplementedException, ExecutionErrorException{
		while(PC <= instructions.size()){
			nextInstr();
		}
	}
	
	public static class TagManager{
		
		private int instruction = 1;
		
		private static Map<String, Integer> tags;
		private static TagManager instance = null;
		
		private TagManager(){
			tags = new HashMap<String, Integer>();
		}
		
		public int nextInstr() {
			return ++instruction;
		}
		
		public int getInstr() {
			return instruction;
		}
		
		public static TagManager getInstance(){
			if(instance == null){
				instance = new TagManager();
			}
			
			return instance;			
		}
		
		public Integer getTagDir(String tag){
			return tags.get(tag);
		}
		
		public boolean addTag(String tag, Integer dir){
			if(tags.containsKey(tag)){
				return false;
			}
			else{
				tags.put(tag, dir);
				return true;
			}
		}
		
	}

}

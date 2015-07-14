package ast;

import ast.DTipo0.Tipo;

public class DTipo1 extends Nodo {
	
	public int tam = -1;
	
	public boolean simplificado = false;

	//public Tipos tipo;
	
	public String id;
	
	public DTipoRegistro registro;
	public DTipoObjeto objeto;
	
	//Array
	public String lexTamArray;
	public int valTamArray;
	public DTipo1 tipoArray;

	public Tipo tipo;
	public Declaracion vinculo;
	
	public static DTipo1 tInt(int fila) {
		DTipo1 t = new DTipo1();
		t.fila = fila;
		t.tam = 1;
		t.tipo = Tipo.INT;
		return t;
	}
	
	public static DTipo1 tReal(int fila) {
		DTipo1 t = new DTipo1();
		t.fila = fila;
		t.tipo = Tipo.REAL;
		t.tam = 1;
		return t;
	}
	
	public static DTipo1 tipoid(String id, int fila) {
		DTipo1 t = new DTipo1();
		t.tipo = Tipo.REFID;
		t.fila = fila;
		t.id = id;
		return t;
	}
	
	public static DTipo1 dTipoRegistro(DTipoRegistro dtiporegistro, int fila) {
		DTipo1 t = new DTipo1();
		t.tipo = Tipo.REG;
		t.fila = fila;
		t.registro = dtiporegistro;
		return t;
	}
	
	public static DTipo1 dTipoObjeto(DTipoObjeto dtipoobjeto, int fila) {
		DTipo1 t = new DTipo1();
		t.tipo = Tipo.OBJETO;
		t.fila = fila;
		t.objeto = dtipoobjeto;
		return t;
	}
	
	public static DTipo1 array(DTipo1 dtipo1, String numEntero, int fila) {
		DTipo1 t = new DTipo1();
		t.fila = fila;
		t.tipo = Tipo.ARRAY;
		t.lexTamArray = numEntero;
		t.valTamArray = Integer.parseInt(t.lexTamArray);
		t.tipoArray = dtipo1;
		return t;
	}
}

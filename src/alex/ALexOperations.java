package alex;

import astCUP.ClaseLexica;

public class ALexOperations {
  public AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  
  
	public UnidadLexica unidadId() {
	   return new UnidadLexica(alex.fila(),ClaseLexica.IDENTIFICADOR, alex.lexema());     
	}  
	public UnidadLexica unidadMod() {
	   return new UnidadLexica(alex.fila(),ClaseLexica.MOD, "%");    
	}
	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(),ClaseLexica.DIV, "/");    
	}
	public UnidadLexica unidadPor() {
		return new UnidadLexica(alex.fila(),ClaseLexica.POR, "*");    
	}
	public UnidadLexica unidadMenos() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MENOS, "-");    
	}
	public UnidadLexica unidadIgual() {
		return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL, "==");    
	}
	public UnidadLexica unidadMas() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAS, "+");    
	}
	public UnidadLexica unidadApostrofe() {
		return new UnidadLexica(alex.fila(),ClaseLexica.APOSTROFE, "^");    
	}
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAYORIGUAL, ">=");    
	}
	public UnidadLexica unidadPCierre() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE, ")");    
	}
	public UnidadLexica unidadPap() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PAP, "(");    
	}
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MENORIGUAL, "<=");    
	}
	public UnidadLexica unidadCcierre() {
		return new UnidadLexica(alex.fila(),ClaseLexica.CCIERRE, "]");    
	}
	public UnidadLexica unidadPunto() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PUNTO, ".");    
	}
	public UnidadLexica unidadCapertura() {
		return new UnidadLexica(alex.fila(),ClaseLexica.CAP, "[");    
	}
	public UnidadLexica unidadAnd() {
		return new UnidadLexica(alex.fila(),ClaseLexica.AND, "&&");    
	}
	public UnidadLexica unidadPycoma() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PYCOMA, ";");    
	}
	public UnidadLexica unidadSep() {
		return new UnidadLexica(alex.fila(),ClaseLexica.SEPARADOR, "#");    
	}
	public UnidadLexica unidadComa() {
		return new UnidadLexica(alex.fila(),ClaseLexica.COMA, ",");    
	}
	public UnidadLexica unidadOr() {
		return new UnidadLexica(alex.fila(),ClaseLexica.OR, "||");    
	}
	public UnidadLexica unidadReferencia() {
	   return new UnidadLexica(alex.fila(),ClaseLexica.REFERENCIA, "&");     
  }
	public UnidadLexica unidadNumReal() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NUMREAL, alex.lexema());     
	}
	public UnidadLexica unidadNumEntero() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NUMENTERO, alex.lexema());     
	}
	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MENOR, "<");     
	}
	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAYOR, ">");     
	}
	public UnidadLexica unidadAsig() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ASIG, "=");     
	}
	public UnidadLexica unidadNot() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NOT, "!");     
	}

	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(),ClaseLexica.EOF, "<EOF>");    
	}
	public UnidadLexica unidadPcierre() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE, ")");    
	}
	public UnidadLexica unidadIn() {
		return new UnidadLexica(alex.fila(),ClaseLexica.IN, "in");  
	}
	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(),ClaseLexica.IF, "if");  
	}
	public UnidadLexica unidadDo() {
		return new UnidadLexica(alex.fila(),ClaseLexica.DO, "do");  
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexica(alex.fila(),ClaseLexica.DISTINTO, "!=");  
	}
	public UnidadLexica unidadInt() {
		return new UnidadLexica(alex.fila(),ClaseLexica.INT, "int");  
	}
	public UnidadLexica unidadOut() {
		return new UnidadLexica(alex.fila(),ClaseLexica.OUT, "out");  
	}
	public UnidadLexica unidadRec() {
		return new UnidadLexica(alex.fila(),ClaseLexica.REC, "rec");  
	}
	public UnidadLexica unidadEnd() {
		return new UnidadLexica(alex.fila(),ClaseLexica.END, "end");  
	}
	public UnidadLexica unidadFun() {
		return new UnidadLexica(alex.fila(),ClaseLexica.FUN, "fun");  
	}
	public UnidadLexica unidadTipo() {
		return new UnidadLexica(alex.fila(),ClaseLexica.TIPO, "tipo");  
	}
	public UnidadLexica unidadThis() {
		return new UnidadLexica(alex.fila(),ClaseLexica.THIS, "this");  
	}
	public UnidadLexica unidadThen() {
		return new UnidadLexica(alex.fila(),ClaseLexica.THEN, "then");  
	}
	public UnidadLexica unidadNull() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NULL, "null");  
	}
	public UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(),ClaseLexica.REAL, "real");  
	}
	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ELSE, "else");  
	}
	public UnidadLexica unidadFree() {
		return new UnidadLexica(alex.fila(),ClaseLexica.FREE, "free");  
	}
	public UnidadLexica unidadEndif() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ENDIF, "endif");  
	}
	public UnidadLexica unidadElsif() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ELSIF, "elsif");  
	}
	public UnidadLexica unidadAlloc() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ALLOC, "alloc");  
	}
	public UnidadLexica unidadSuper() {
		return new UnidadLexica(alex.fila(),ClaseLexica.SUPER, "super");  
	}
	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(),ClaseLexica.WHILE, "while");  
	}
	public UnidadLexica unidadCastInt() {
		return new UnidadLexica(alex.fila(),ClaseLexica.CASTINT, "(int)");  
	}
	public UnidadLexica unidadObject() {
		return new UnidadLexica(alex.fila(),ClaseLexica.OBJECT, "object");  
	}
	public UnidadLexica unidadReturn() {
		return new UnidadLexica(alex.fila(),ClaseLexica.RETURN, "return");  
	}
	public UnidadLexica unidadEndrec() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ENDREC, "endrec");  
	}
	public UnidadLexica unidadMethod() {
		return new UnidadLexica(alex.fila(),ClaseLexica.METHOD, "method");  
	}
	public UnidadLexica unidadCastReal() {
		return new UnidadLexica(alex.fila(),ClaseLexica.CASTREAL, "(real)");  
	}
	public UnidadLexica unidadPointer() {
		return new UnidadLexica(alex.fila(),ClaseLexica.POINTER, "pointer");  
	}
	public UnidadLexica unidadReturns() {
		return new UnidadLexica(alex.fila(),ClaseLexica.RETURNS, "returns");  
	}
	public UnidadLexica unidadExtends() {
		return new UnidadLexica(alex.fila(),ClaseLexica.EXTENDS, "extends");  
	}
	public UnidadLexica unidadEndwhile() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ENDWHILE, "endwhile");  
	}
	public UnidadLexica unidadEndobject() {
		return new UnidadLexica(alex.fila(),ClaseLexica.ENDOBJECT, "endobject");  
	}
}

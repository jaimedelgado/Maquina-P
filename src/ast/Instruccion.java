package ast;

public class Instruccion extends Nodo {
	public enum Tipos{ RETURN, ASIG, IF, WHILE, INVOCACIONFUNMET, IN, OUT, ALLOC, FREE}
	public Tipos tipo;
	public IAsig iasig;
	public IIn iin;
	public IOut iout;
	public IAlloc ialloc;
	public IFree ifree;
	public IIf iif;
	public IWhile iwhile;
	public IReturn ireturn;
	public InvocacionFunMet invocacionfunmet;
	
	public int comienzo;
	public int fin;
	
	public static Instruccion iAsig(IAsig iasig, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.iasig=iasig;
		i.tipo = Tipos.ASIG;
		return i;
	}
	public static Instruccion iIn(IIn iin, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.iin=iin;
		i.tipo = Tipos.IN;
		return i;
	}
	public static Instruccion iOut(IOut iout, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.iout=iout;
		i.tipo = Tipos.OUT;
		return i;
	}
	public static Instruccion iAlloc(IAlloc ialloc, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.ialloc=ialloc;
		i.tipo = Tipos.ALLOC;
		return i;
	}
	public static Instruccion iFree(IFree ifree, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.ifree=ifree;
		i.tipo = Tipos.FREE;
		return i;
	}
	public static Instruccion iIf(IIf iif, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.iif=iif;
		i.tipo = Tipos.IF;
		return i;
	}
	public static Instruccion iWhile(IWhile iwhile, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.iwhile=iwhile;
		i.tipo = Tipos.WHILE;
		return i;
	}
	public static Instruccion iReturn(IReturn ireturn, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.ireturn=ireturn;
		i.tipo = Tipos.RETURN;
		return i;
	}
	public static Instruccion invocFunMet(InvocacionFunMet invoc, int fila){
		Instruccion i = new Instruccion();
		i.fila = fila;
		i.invocacionfunmet=invoc;
		i.tipo = Tipos.INVOCACIONFUNMET;
		return i;
	}
}

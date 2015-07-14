package ast;

public class Designador extends Nodo {
	public enum Tipo {ARRAY, ATRIBUTO, PUNTERO, THIS, SUPER, ID};
	public Tipo tipo;
	public Declaracion vinculo;
	public String id;
	public OtroDesignador otrodesignador;
	public DesigArray desigarray;
	public DesigAtributo desigatributo;
	public DesigPuntero desigpuntero;
	public DTipo0 dtipo0;
	public static Designador var(String id, int fila){
		Designador d = new Designador();
		d.id=id;
		d.fila=fila;
		d.tipo=Tipo.ID;
		return d;
	}
	public static Designador otroDesignador(OtroDesignador otrodesignador, int fila){
		Designador d = new Designador();
		d.fila=fila;
		d.otrodesignador = otrodesignador;
		d.tipo=otrodesignador.tipo;
		d.id = otrodesignador.id;
		return d;
	}
	public static Designador desigArray(DesigArray desigarray, int fila){
		Designador d = new Designador();
		d.fila=fila;
		d.desigarray = desigarray;
		d.id = desigarray.designador.id;
		d.tipo=Tipo.ARRAY;
		return d;
	}
	public static Designador desigAtributo(DesigAtributo desigatributo, int fila){
		Designador d = new Designador();
		d.fila=fila;
		d.desigatributo = desigatributo;
		d.id = desigatributo.id;
		d.tipo=Tipo.ATRIBUTO;
		return d;
	}
	public static Designador desigPuntero(DesigPuntero desigpuntero, int fila){
		Designador d = new Designador();
		d.fila=fila;
		d.tipo=Tipo.PUNTERO;
		d.desigpuntero = desigpuntero;
		d.id=desigpuntero.designador.id;
		return d;
	}
}

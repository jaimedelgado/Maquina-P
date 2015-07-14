package ast;

public class IIf extends Nodo {
public Casos casos;
public ParteElse parteelse;

	public static IIf iIf(Casos casos, ParteElse parteelse, int fila){
		IIf i = new IIf();
		i.fila=fila;
		i.casos=casos;
		i.parteelse = parteelse;
		return i;
	}
}

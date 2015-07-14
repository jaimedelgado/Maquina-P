package ast;

public class DecFun extends Declaracion {
	
	public static int comienzo = -1;

	public int dirComienzo;
		
	public Cabecera cabecera;
	public Programa programa;
	
	public int tamParamMasVariables = -1;
	
	public static DecFun decFun(Cabecera cabecera, Programa programa, String idRet, int fila){
		DecFun df = new DecFun();
		df.fila = fila;
		df.id = idRet;
		df.cabecera = cabecera;
		df.programa = programa;
		df.dtipo0 = cabecera.tiporet.dtipo0;
		df.clase = Clases.FUNCION;
		df.tamParamMasVariables = -1;
		return df;
	}
	
	
	/*public Parametros getParametros(){
		return cabecera.getParametros();
	}
	*/
	//Cabecera.getParametros
	/*public List<Declaracion> getParametros() {
		List<Declaracion> declaraciones = new ArrayList<Declaracion>();
		
		if(parametros.listaParametros != null){
			declaraciones.addAll(parametros.listaParametros.getParametros());
		}
		
		return declaraciones;
	}
	
	//Programa.getDeclaraciones
	public List<Declaracion> getDeclaraciones() {
		List<Declaracion> declaraciones = new ArrayList<Declaracion>();
		if(seccionTipos.decTipos != null){
			declaraciones.addAll(seccionTipos.decTipos.getDecTipos());
		}
		if(seccionVariables.decVariables != null){
			declaraciones.addAll(seccionVariables.decVariables.getDecVariables());
		}
		if(seccionSubprogramas.decSubprogramas != null){
			declaraciones.addAll(seccionSubprogramas.decSubprogramas.getDecSubprogramas());
		}
		
		return declaraciones;
	}
	*/
}

package ast;

public class OtroDesignador extends Designador {
	public static OtroDesignador dThis(int fila){
		OtroDesignador o = new OtroDesignador();
		o.fila=fila;
		o.tipo=Tipo.THIS;
		return o;
	}
	public static OtroDesignador dSuper(int fila){
		OtroDesignador o = new OtroDesignador();
		o.fila=fila;
		o.tipo=Tipo.SUPER;
		return o;
	}
}

package maquinap.valores;

public class Valor {
	
	protected String actualValue;
	protected Object innerValue;

	public Valor(){
		actualValue = null;
	}

	public static Valor GetValor(String valor) {
		try {
			Class<?> valorClass = (Class<?>) Class.forName("maquinap.valores." + valor);

			return (Valor)valorClass.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getStringValue(){
		return actualValue;
	}

	public boolean setValue(String value) {
		actualValue = value;
		
		return setInnerValue(actualValue);
	}
	
	protected boolean setInnerValue(String value){
		this.innerValue = value;
		
		return true;
	}

	public String toString() {
		return actualValue.toString();
	}
	
	public Object getInnerValue() {
		return this.innerValue;
	}
}

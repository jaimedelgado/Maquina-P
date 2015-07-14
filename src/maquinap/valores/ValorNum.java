package maquinap.valores;

public class ValorNum extends Valor {

	private Number innerValue;

	public ValorNum() {
		innerValue = null;
	}

	public ValorNum(double d) {
		setValue(String.valueOf(d));
		this.innerValue = d;
	}

	public ValorNum(Valor pop) {
		if(pop == null){
			this.actualValue = "0";
			this.innerValue = 0;
		}
		else{
			this.actualValue = pop.getStringValue();
			this.innerValue = (Number) Double.parseDouble(pop.getStringValue());
		}
	}

	public ValorNum suma(ValorNum otro) {
		return new ValorNum(innerValue.doubleValue()
				+ otro.getInnerValue().doubleValue());
	}

	public ValorNum divide(ValorNum otro) {
		return new ValorNum(innerValue.doubleValue()
				/ otro.getInnerValue().doubleValue());
	}
	
	public ValorNum multiplica(ValorNum otro) {
		return new ValorNum(innerValue.doubleValue()
				* otro.getInnerValue().doubleValue());
	}

	@Override
	protected boolean setInnerValue(String value) {
		try {
			this.innerValue = Double.valueOf(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return innerValue.toString();
	}

	public Number getInnerValue() {
		return innerValue;
	}

	public int toInt() {
		return innerValue.intValue();
	}

	public Valor resta(ValorNum otro) {
		return new ValorNum(innerValue.doubleValue()
				- otro.getInnerValue().doubleValue());
	}

	public Valor negativo() {
		return new ValorNum(-innerValue.doubleValue());
	}

	public Valor modulo(ValorNum operador2) {
		return new ValorNum(innerValue.doubleValue() % operador2.getInnerValue().doubleValue());
	}

}

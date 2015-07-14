package maquinap.valores;

import maquinap.MaquinaP;

public class ValorEnt extends ValorNum {
	
	private Integer innerValue;
	
	private boolean isTag = false;
	
	public ValorEnt() {
		innerValue = null;
	}
	
	public ValorEnt(int i){
		setValue(String.valueOf(i));
		this.innerValue = i;
	}
	
	public ValorEnt(Valor pop) {
		if(pop == null){
			this.actualValue = "0";
			this.innerValue = 0;
		}
		else{
			try{
				this.actualValue = pop.getStringValue();
				this.innerValue = (Integer) Integer.parseInt(pop.getStringValue());
			}
			catch(NumberFormatException e){
				this.innerValue = (int) Double.parseDouble(pop.getStringValue());
			}
		}
	}
	
	@Override
	protected boolean setInnerValue(String value) {
		try {
			if(value.startsWith("[") && value.endsWith("]")){
				isTag = true;
			}
			else{
				this.innerValue = Integer.valueOf(value);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if(isTag){
			return actualValue;
		}
		else{
			return innerValue.toString();
		}
	}
	
	public int toInt() {
		if(isTag){
			return MaquinaP.TagManager.getInstance().getTagDir(actualValue);
		}
		else{
			return innerValue.intValue();
		}
	}

}

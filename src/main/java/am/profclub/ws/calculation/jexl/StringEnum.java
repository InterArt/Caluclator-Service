package am.profclub.ws.calculation.jexl;

import java.util.*;

public abstract class StringEnum extends NotAnEnum {

    public StringEnum(String val) {
		super(val);
    }

	public StringEnum(String val, String label){
		super(val, label);
	}


	public String getValueAsString() {
		return (String) getValue();
	}


	public boolean equals( StringEnum val ) {
		return val != null && getValueAsString().equals(val.getValueAsString());
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StringEnum && equals((StringEnum) obj);
	}

	@Override
	public int hashCode() {
		return getValueAsString().hashCode();
	}

	protected static StringEnum getEnum(String val, List map) {
		if( val == null) return null;
		return (StringEnum) ((ArrayListMap)map).getValue(val);
    }
}


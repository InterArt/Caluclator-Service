package am.profclub.ws.calculation.jexl;

import org.apache.log4j.*;

import java.lang.reflect.*;
import java.util.*;

//This class was named "Enum" - which conflicts with the JLS reserved Enum, as in java.lang.Enum. Interestingly, this is not an enumerated type.
public abstract class NotAnEnum implements KeyedEnum {

	private static final Logger log = Logger.getLogger(NotAnEnum.class);

    protected String _labelKey;
	protected Object _value;

	protected NotAnEnum(Object val){
		this( val, null);
	}

    protected NotAnEnum(Object val, String label) {
		_value = val;
		_labelKey = label;
    }

	protected static List getAllEnums() { return new ArrayListMap(); }

	public static String[] getKaminoAttributes() {
		return new String[0];
	}

	public static Map<Object, KeyedEnum> getEnumValues(Class<? extends KeyedEnum> enumClass) {
		Map values = new LinkedHashMap();
		try {
			Method getValueMethod = KeyedEnum.class.getMethod("getValue");

			for (Field field : enumClass.getDeclaredFields()) {
				if (field.getType() != enumClass || !Modifier.isStatic(field.getModifiers())) continue;

				Object enumValue = field.get(null);
				Object value = getValueMethod.invoke(enumValue);

				values.put(value, enumValue);
			}
		} catch (IllegalAccessException e) {
			log.error("error getting enum values for class: " + enumClass, e);
		} catch (NoSuchMethodException e) {
			log.error("error getting enum values for class: " + enumClass, e);
		} catch (InvocationTargetException e) {
			log.error("error getting enum values for class: " + enumClass, e);
		}
		return values;
	}

	public Object getValue(){
		return _value;
	}

    public String getLabel() {
        return _labelKey;
    }

    public String getKeyLabel() {
      return _labelKey;
    }

	@Override
	public int hashCode(){
		return getValue().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotAnEnum) {
			return equals((NotAnEnum)obj);
		}
		return false;
	}

	public boolean equals( NotAnEnum val ){
		if (val == null){
			return false;
		}

		return getValue().equals(val.getValue());
	}


	public String toString(){
		if( _value == null) return null;
		return _value.toString();
	}

	public String getID() {
		return getLabel();
	}
}

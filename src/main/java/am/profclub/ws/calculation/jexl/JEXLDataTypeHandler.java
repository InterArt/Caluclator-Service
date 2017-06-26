package am.profclub.ws.calculation.jexl;


import java.security.*;
import java.util.*;

public abstract class JEXLDataTypeHandler {

	public static JEXLDataTypeHandler getDataTypeHandler(Class cls){
		return (JEXLDataTypeHandler) __dataTypeHandlers.get(cls);
	}

	public abstract boolean evaluate(JEXLEvaluator jeval, Object fieldVal, String value, String[] values, String rangeValue) throws RuntimeException;

	////////////////////////////////////////////////// 
	// data type handlers
	private static Map __dataTypeHandlers;
	static {
		__dataTypeHandlers = new HashMap();
		__dataTypeHandlers.put(String.class, new JEXLDataTypeHandler() {
			public boolean evaluate(JEXLEvaluator jeval, Object fieldVal, String value, String[] values, String rangeValue) throws RuntimeException {
				if (jeval.getExpectsArray()) {
					return jeval.evaluate(fieldVal, values, rangeValue);
				} else {
					return jeval.evaluate(fieldVal, value, rangeValue);
				}
			}
		});
		__dataTypeHandlers.put(Integer.class, new JEXLDataTypeHandler() {
			public boolean evaluate(JEXLEvaluator jeval, Object fieldVal, String value, String[] values, String rangeValue) throws RuntimeException {
				if (jeval.getExpectsArray()) {
					Integer[] testVals = ArrayUtil.wrapIntArray(StringUtils.convertToIntArray(values));
					Integer testRangeValue = new Integer(StringUtils.convertToInt(rangeValue));
					return jeval.evaluate(fieldVal, testVals, testRangeValue);
				} else {
					int val;
					try {
						val = StringUtils.convertToInt(value);
					} catch (InvalidParameterException e) {

						// handle boolean to int conversion
						if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")) {
							val = 1;
						} else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("f")) {
							val = 0;
						} else {
							throw e;
						}

					}

					Integer testVal = new Integer(val);
					Integer testRangeValue = new Integer(StringUtils.convertToInt(rangeValue));
					return jeval.evaluate(fieldVal, testVal, testRangeValue);
				}
			}
		});
		__dataTypeHandlers.put(Boolean.class, new JEXLDataTypeHandler() {
			public boolean evaluate(JEXLEvaluator jeval, Object fieldVal, String value, String[] values, String rangeValue) throws RuntimeException {
				if (jeval.getExpectsArray()) {
					Boolean[] testVals = ArrayUtil.wrapBooleanArray(StringUtils.convertToBooleanArray(values));
					Boolean testRangeValue = new Boolean(StringUtils.convertToBoolean(rangeValue));
					return jeval.evaluate(fieldVal, testVals, testRangeValue);
				} else {
					Boolean testVal = new Boolean(StringUtils.convertToBoolean(value));
					Boolean testRangeValue = new Boolean(StringUtils.convertToBoolean(rangeValue));
					return jeval.evaluate(fieldVal, testVal, testRangeValue);
				}
			}
		});
	}
}

package am.profclub.ws.calculation.jexl;

import org.apache.commons.jexl3.*;
import org.apache.log4j.*;

import java.util.*;

public class JEXLExpressionHelper implements QueryConstants {

	static Logger log = Logger.getLogger(JEXLExpressionHelper.class);

	public static final String ARRAY_DELIM = "##";
	public static final String ARRAY_DELIM_ENCODING = "#^#^";
	public static final String ARRAY_NULL_VALUE = "null";
	public static final String EMPTY_STRING = "";

	protected JEXLExpressionHelper(){
	}

	public static Object eval(JexlContext context, String functionName, String parameters, Map<String, JexlExpression> subExpression) {
		return eval(context, functionName, parameters, false, subExpression);
	}

	public static Object eval(JexlContext context, String functionName, String parameters, boolean acceptExpressionsAsResult, Map<String, JexlExpression> subExpression) {
		final ExpressionTypeEnum function = ExpressionTypeEnum.getEnum(functionName.toUpperCase());
		if (function == null) {
			return EMPTY_STRING;
		}

		// Evaluate each parameter in the function call.
		final List<Object> evaluated = new ArrayList<>();

		final List<String> names = StringUtils.split(parameters, ',');
		for (int i = 0; i < names.size(); i++) {
			final String name = names.get(i);
			try {
				// Each parameter is a named subexpression in the formula.
				final JexlExpression expression = subExpression.get(name);
				Object value;

				value = evaluateExpression(expression, context);

				if (value == null) {
					value = EMPTY_STRING;
				}

				evaluated.add(value);
			} catch (Exception e) {
				evaluated.add(EMPTY_STRING);
			}
		}

		return function.evaluate(context, evaluated.toArray());
	}

	public static String convertStringArrayToJEXLString(String[] vals){
		if( vals == null) return null;
		StringBuilder sb = new StringBuilder(vals.length * 128);
		for( int i = 0; i < vals.length; i++){
			if( i > 0){
				sb.append(ARRAY_DELIM);
			}
			if (vals[i] != null) {
				sb.append(vals[i].replace(ARRAY_DELIM, ARRAY_DELIM_ENCODING));
			} else {
				sb.append(ARRAY_NULL_VALUE);
			}
		}
		return sb.toString();
	}

	public static String[] convertJEXLStringToStringArray(String val){
		if (val == null) return null;

		List<String> parts = StringUtils.split(val, ARRAY_DELIM);
		String[] vals = parts.toArray(new String[parts.size()]);

		for (int i = 0; i < vals.length; i++) {
			if (ARRAY_NULL_VALUE.equals(vals[i])) {
				vals[i] = null;
			} else {
				vals[i] = vals[i].replace(ARRAY_DELIM_ENCODING, ARRAY_DELIM);
			}
		}

		return vals;
	}


	public static String ceil(Object val) {
		if( StringUtils.isBlank(val) ) return EMPTY_STRING;
		Object retVal;
		
		try {
			retVal = Math.ceil(Double.parseDouble(val.toString()));
			if( retVal != null) {
				return retVal.toString();
			}
		} catch (NumberFormatException nfe) {
			log.debug(nfe);
		}
		
		return EMPTY_STRING;
	}

	public static String round(Object val) {
		if( StringUtils.isBlank(val) ) return EMPTY_STRING;
		Object retVal;
		
		try {
			retVal = Math.round(Double.parseDouble(val.toString()));
			if( retVal != null) {
				return retVal.toString();
			}
		} catch (NumberFormatException nfe) {
			log.debug(nfe);
		}
		
		return EMPTY_STRING;
	}

	public static String floor(Object val) {
		if( StringUtils.isBlank(val) ) return EMPTY_STRING;
		Object retVal;
		
		try {
			retVal = Math.floor(Double.parseDouble(val.toString()));
			if( retVal != null) {
				return retVal.toString();
			}
		} catch (NumberFormatException nfe) {
			log.debug(nfe);
		}
		
		return EMPTY_STRING;
	}

	public static String min(Object val) {
		// implement
		return StringUtils.valueOf(val);
	}

	public static String max(Object val) {
		// implement
		return StringUtils.valueOf(val);
	}

	public static String sum(Object...operands) {
		if( StringUtils.isBlank(operands) ) return EMPTY_STRING;
		
		try {
			double sum = 0;
			for(Object o: operands) {
				sum += Double.parseDouble(o.toString());
			}

			return String.valueOf(sum);
		} catch (NumberFormatException nfe) {
			log.debug(nfe);
		}
		
		return EMPTY_STRING;
	}

	public static String avg(Object...operands) {
		if( StringUtils.isBlank(operands) ) return EMPTY_STRING;
		
		try {
			double sum = 0;
			for(Object o: operands) {
				sum += Double.parseDouble(o.toString());
			}

			return String.valueOf(sum/operands.length);
		} catch (NumberFormatException nfe) {
			log.debug(nfe);
		}
		
		return EMPTY_STRING;
	}

	public static Object evaluateExpression(JexlExpression expression, JexlContext jexlContext) throws Exception {
		Object result = expression.evaluate(jexlContext);
		while (result instanceof JexlExpression) {
			result = ((JexlExpression)result).evaluate(jexlContext);
		}
		return result;
	}

	private static boolean getBooleanOperand(Object operand) {
		if (operand instanceof Boolean)
			return (Boolean) operand;
		else if (operand instanceof Number)
			return ((Number) operand).doubleValue() != 0.0;
		else if (operand instanceof String)
			return Boolean.valueOf((String) operand);
		else if (operand != null)
			return Boolean.valueOf(operand.toString());
		else
			return false;
	}
}




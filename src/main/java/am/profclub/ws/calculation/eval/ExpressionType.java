package am.profclub.ws.calculation.eval;

import am.profclub.ws.calculation.jexl.*;
import am.profclub.ws.calculation.util.*;
import org.apache.log4j.*;

import java.math.*;
import java.text.*;
import java.util.*;

/**
 * Created by admin on 7/4/17.
 */
public enum ExpressionType implements IExpressionType {

	SQRT ("SQRT", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double result = Math.sqrt(getDoubleOperand(operands[0]));
				if (!Double.isNaN(result) && !Double.isInfinite(result))
					return result;
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	},

	SUM ("SUM", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double result = 0;
				for(Object operand: operands) {
					result += getDoubleOperand(operand);
				}

				return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	SUBTRACT ("SUB", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double result = getDoubleOperand(operands[0]);
				for(int i = 1; i < operands.length; i++) {
					result -= getDoubleOperand(operands[i]);
				}

				return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	PRODUCT ("PROD", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double result = 1;
				for(Object operand: operands) {
					result *= getDoubleOperand(operand);
				}

				return result;
			} catch (Exception e) {
				//TODO
			}

			return null;
		}
	},

	DIVIDE ("DIV", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double result = getDoubleOperand(operands[0]);
				for(int i = 1; i < operands.length; i++) {
					result /= getDoubleOperand(operands[i]);
				}
				if (!Double.isNaN(result) && !Double.isInfinite(result))
					return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	MIN ("MIN", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double min = Double.POSITIVE_INFINITY;
				for(Object operand: operands) {
					double val = getDoubleOperand(operand);
					if(val < min) min = val;
				}

				return min;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	MAX ("MAX", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double max = Double.NEGATIVE_INFINITY;
				for(Object operand: operands) {
					double val = getDoubleOperand(operand);

					if(val > max) max = val;
				}

				return max;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	AVERAGE ("AVERAGE", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double sum = 0;
				for(Object operand: operands) {
					sum += getDoubleOperand(operand);
				}

				return sum / operands.length;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	CEILING ("CEIL", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.ceil(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	FLOOR ("FLOOR",1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.floor(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	ROUND ("ROUND",1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);

				// Do not attempt to round NaN or inifinite values
				if (! (Double.isNaN(val) || Double.isInfinite(val))) {
					int numberOfDecimals = 0;
					if (operands.length > 1 && !StringUtils.isBlank(operands[1])) {
						numberOfDecimals = getIntOperand(operands[1]);
					}

					double factor = Math.pow(10, numberOfDecimals);
					return ((Math.round(val * factor)) / factor);
				} else {
					// Returning NaN or null has the same outcome, but this way will engage
					// the evaluation at com.attask.biz.DataExtensionMethods.convertToNumber()
					return Double.NaN;
				}
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	POWER ("POWER", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double op1 = getDoubleOperand(operands[0]);
				double op2 = getDoubleOperand(operands[1]);

				double result = Math.pow(op1, op2);
				if (!Double.isNaN(result) && !Double.isInfinite(result))
					return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	ABS ("ABS",1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.abs(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	LN ("LN", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				double result = Math.log(val);
				if (!Double.isNaN(result) && !Double.isInfinite(result))
					return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	LOG ("LOG", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double op1 = getDoubleOperand(operands[0]);
				double op2 = getDoubleOperand(operands[1]);

				double result = Math.log(op2) / Math.log(op1);
				if (!Double.isNaN(result) && !Double.isInfinite(result))
					return result;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	SORTASCSTRING ("SORTASCSTRING", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				String[] result = new String[operands.length];
				for(int i = 0; i < operands.length; i++) {
					result[i] = getStringOperand(operands[i]);
				}

				Arrays.sort(result);
				return StringUtils.toStringFromStringArray(result, ", ");
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	SORTDESCSTRING ("SORTDESCSTRING", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				String[] result = new String[operands.length];
				for(int i = 0; i < operands.length; i++) {
					result[i] = getStringOperand(operands[i]);
				}

				Arrays.sort(result, Collections.reverseOrder());
				return StringUtils.toStringFromStringArray(result, ", ");
			} catch (Exception e) {
				//TODO;

			}
			return null;
		}
	},

	LOWER ("LOWER",1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			return getStringOperand(operands[0]).toLowerCase();
		}
	},

	UPPER ("UPPER",1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			return getStringOperand(operands[0]).toUpperCase();
		}
	},

	CONCATENATE ("CONCAT", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			StringBuilder concat = new StringBuilder();
			for(Object operand: operands) {
				concat.append(getStringOperand(operand));
			}

			return concat.toString();
		}
	},

	LENGTH ("LEN", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return 0;

			return getStringOperand(operands[0]).length();
		}
	},

	REPLACE ("REPLACE", 3) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			String source = getStringOperand(operands[0]);
			String replace = getStringOperand(operands[1]);
			String with = getStringOperand(operands[2]);

			return source.replace(replace, with);
		}
	},

	SUBSTRING ("SUBSTR", 3) {
		public Object evaluate(Object[] operands) {
			// the third argument here is optional
			if (!checkArgs(operands, 2)) return "";

			try {
				String source = getStringOperand(operands[0]);
				int beginIndex = getIntOperand(operands[1]);
				if(operands.length >= 3 && !StringUtils.isBlank(operands[2])) {
					int endIndex = getIntOperand(operands[2]);
					if (endIndex > source.length()) endIndex = source.length();
					return source.substring(beginIndex, endIndex);
				}
				return source.substring(beginIndex);
			} catch (Exception e) {
				//TODO;
			}

			return "";
		}
	},

	CONTAINS ("CONTAINS", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String findText = getStringOperand(operands[0]);
				String withinText = getStringOperand(operands[1]);

				return withinText.contains(findText);
			} catch (Exception e) {
				//TODO;
			}

			return false;
		}
	},

	SEARCH ("SEARCH", 3) {
		public Object evaluate(Object[] operands) {
			// the third argument here is optional
			if (!checkArgs(operands, 2)) return "";

			try {
				String findText = getStringOperand(operands[0]);
				String withinText = getStringOperand(operands[1]);
				int start = 0;
				if(operands.length >= 3 && !StringUtils.isBlank(operands[2])) {
					start = getIntOperand(operands[2]);
				}

				return withinText.indexOf(findText, start);
			} catch (Exception e) {
				//TODO;
			}

			return -1;
		}
	},

	TRIM ("TRIM", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			return getStringOperand(operands[0]).trim();
		}
	},

	LEFT ("LEFT", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String source = getStringOperand(operands[0]);
				int index = getIntOperand(operands[1]);

				if(index > source.length()) index = source.length();

				return source.substring(0, index);
			} catch (Exception e) {
				//TODO;
			}

			return "";
		}
	},

	RIGHT ("RIGHT", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String source = getStringOperand(operands[0]);
				int index = source.length() - getIntOperand(operands[1]);
				if(index < 0) index = 0;

				return source.substring(index);
			} catch (Exception e) {
				//TODO;
			}

			return "";
		}
	},

	IF ("IF", 3) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, 2, true)) return null;

			try {
				Object trueValue = operands[1];
				Object falseValue = operands.length >= 3 ? operands[2] : "";

				boolean condition = getBooleanOperand(operands[0]);
				return condition ? trueValue : falseValue;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	CASE ("CASE", 0) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				int index = getIntOperand(operands[0]);
				if (index < 1 || index > operands.length - 1) return null;
				return operands[index];
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	ISBLANK ("ISBLANK", 1) {
		public Object evaluate(Object[] operands) {
			if (operands == null || operands.length < getNumberOfArguments()) return null;

			try {
				return StringUtils.isBlank(getStringOperand(operands[0]));
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	IN ("IN", 2) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				Object searchValue = operands[0];
				for (int i=1; i<operands.length; i++) {
					if (StringUtils.objectsEqual(searchValue, operands[i]))
						return true;
				}
				return false;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	IFIN ("IFIN", 4) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				Object searchValue = operands[0];
				Object trueValue = operands[operands.length - 2];
				Object falseValue = operands[operands.length - 1];

				for (int i = 1; i < operands.length - 2; i++) {
					if (StringUtils.objectsEqual(searchValue, operands[i]))
						return trueValue;
				}
				return falseValue;
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	NUMBER ("NUMBER", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				return getNumericOperand(operands[0]);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	},

	STRING ("STRING", 1) {
		public Object evaluate(Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			int decimal = -1;
			if (operands.length > 1 && operands[1] instanceof Number) {
				decimal = ((Number)operands[1]).intValue();
			}

			if (operands[0] instanceof Number) {
				NumberFormat nf = NumberFormat.getInstance();
				if (decimal != -1) {
					nf.setMaximumFractionDigits(decimal);
					nf.setRoundingMode(RoundingMode.FLOOR);
					nf.setMinimumFractionDigits(decimal);
				}

				try {
					return nf.format(((Number)operands[0]).doubleValue());
				} catch (Exception e) {
					return StringUtils.toString(operands[0]);
				}
			}

			return StringUtils.toString(operands[0]);
		}
	};

	private static Logger log = Logger.getLogger(ExpressionType.class);

	private ExpressionType(String val, int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
		this.val = val;
	}

	private int numberOfArguments;
	private String val;

	public int getNumberOfArguments() {
		return numberOfArguments;
	}

	public String getVal() {
		return val;
	}

	static boolean checkArgs(Object[] operands, int expectedLength) {
		return checkArgs(operands, expectedLength, false);
	}

	static boolean checkArgs(Object[] operands, int expectedLength, boolean allowBlank) {
		if (allowBlank) {
			return operands != null && operands.length >= expectedLength;
		}

		if (operands != null && operands.length >= expectedLength) {
			for (int i = 0; i < expectedLength; i++) {
				Object operand = operands[i];
				if (operand == null || StringUtils.isBlank(operand)) return false;
			}

			return true;
		}

		return false;
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

	private static double getDoubleOperand(Object operand) {
		return getNumericOperand(operand).doubleValue();
	}

	private static int getIntOperand(Object operand) {
		return getNumericOperand(operand).intValue();
	}

	private static Number getNumericOperand(Object operand) {
		try {
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			if (operand instanceof Number)
				return (Number) operand;
			else if (operand instanceof String && ((String) operand).length() > 0)
				return nf.parse((String) operand);
			else if (operand instanceof String)
				return 0.0;
			else if (operand instanceof Boolean)
				return ((Boolean) operand) ? 1 : 0;
			else if (operand != null)
				return nf.parse(operand.toString());
			else
				return 0.0;
		} catch (ParseException e) {
			return 0.0;
		}
	}

	private static String getStringOperand(Object operand) {
		if (operand == null) return "";

		if (operand instanceof String) {
			return (String) operand;
		}
		return StringUtils.toString(operand);
	}

	public static ExpressionType getEnum(String functionName) {
		if (StringHelper.isBlank(functionName)) return null;
		for (ExpressionType type : ExpressionType.values()) {
			if (type.getVal().equals(functionName)) return type;
		}

		return null;
	}

	public static Object evaluate(String functionName, Object[] args) {
		IExpressionType expressionType = getEnum(functionName);
		return expressionType.evaluate(args);
	}

	public static Set<String> functions() {
		Set<String> functions = new HashSet<>();
		for (ExpressionType type : ExpressionType.values()) {
			functions.add(type.getVal());
		}
		return functions;
	}

	static final Set<String> functions = functions();
}

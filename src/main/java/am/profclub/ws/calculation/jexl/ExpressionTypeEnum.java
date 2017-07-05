package am.profclub.ws.calculation.jexl;

import org.apache.commons.jexl3.*;
import org.apache.log4j.*;

import java.math.*;
import java.text.*;
import java.util.*;

/**
 *   ExpressionTypeEnum  used to define what type of aggregate this is. Not used in the API.
 */
public class ExpressionTypeEnum  extends StringEnum {

	private static Logger log = Logger.getLogger(ExpressionTypeEnum.class);

	public static final int VARARGS = 0;

	public static final ExpressionTypeEnum SQRT = new ExpressionTypeEnum("SQRT", "enum.expressiontypeenum.sqrt", "enum.expressiontypeenum.sqrt.text", "enum.expressiontypeenum.sqrt.methodcall", 1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum SUM = new ExpressionTypeEnum("SUM", "enum.expressiontypeenum.sum", "enum.expressiontypeenum.sum.text", "enum.expressiontypeenum.sum.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum SUBTRACT = new ExpressionTypeEnum("SUB", "enum.expressiontypeenum.subtract", "enum.expressiontypeenum.subtract.text", "enum.expressiontypeenum.subtract.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum PRODUCT = new ExpressionTypeEnum("PROD", "enum.expressiontypeenum.product", "enum.expressiontypeenum.product.text", "enum.expressiontypeenum.product.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum DIVIDE = new ExpressionTypeEnum("DIV", "enum.expressiontypeenum.divide", "enum.expressiontypeenum.divide.text", "enum.expressiontypeenum.divide.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum MIN = new ExpressionTypeEnum("MIN", "enum.expressiontypeenum.min", "enum.expressiontypeenum.min.text", "enum.expressiontypeenum.min.methodcall",VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};
	
	public static final ExpressionTypeEnum MAX = new ExpressionTypeEnum("MAX", "enum.expressiontypeenum.max", "enum.expressiontypeenum.max.text", "enum.expressiontypeenum.max.methodcall",VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};
	
	public static final ExpressionTypeEnum AVERAGE = new ExpressionTypeEnum("AVERAGE", "enum.expressiontypeenum.average", "enum.expressiontypeenum.average.text", "enum.expressiontypeenum.average.methodcall",VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};
	
	public static final ExpressionTypeEnum CEILING = new ExpressionTypeEnum("CEIL", "enum.expressiontypeenum.ceil", "enum.expressiontypeenum.ceil.text", "enum.expressiontypeenum.ceil.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.ceil(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};
	
	public static final ExpressionTypeEnum FLOOR = new ExpressionTypeEnum("FLOOR", "enum.expressiontypeenum.floor", "enum.expressiontypeenum.floor.text", "enum.expressiontypeenum.floor.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.floor(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};
	
	public static final ExpressionTypeEnum ROUND = new ExpressionTypeEnum("ROUND", "enum.expressiontypeenum.round", "enum.expressiontypeenum.round.text", "enum.expressiontypeenum.round.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum POWER = new ExpressionTypeEnum("POWER", "enum.expressiontypeenum.power", "enum.expressiontypeenum.power.text", "enum.expressiontypeenum.power.methodcall",2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum ABS = new ExpressionTypeEnum("ABS", "enum.expressiontypeenum.abs", "enum.expressiontypeenum.abs.text", "enum.expressiontypeenum.abs.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return null;

			try {
				double val = getDoubleOperand(operands[0]);
				return Math.abs(val);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};

	public static final ExpressionTypeEnum LN = new ExpressionTypeEnum("LN", "enum.expressiontypeenum.ln", "enum.expressiontypeenum.ln.text", "enum.expressiontypeenum.ln.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum LOG = new ExpressionTypeEnum("LOG", "enum.expressiontypeenum.log", "enum.expressiontypeenum.log.text", "enum.expressiontypeenum.log.methodcall",2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};
	
	public static final ExpressionTypeEnum SORTASCSTRING = new ExpressionTypeEnum("SORTASCSTRING", "enum.expressiontypeenum.sortascstring", "enum.expressiontypeenum.sortascstring.text", "enum.expressiontypeenum.sortascstring.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				String[] result = new String[operands.length];
				for(int i = 0; i < operands.length; i++) {
					result[i] = getStringOperand(jexlContext, operands[i]);
				}

				Arrays.sort(result);
				return StringUtils.toStringFromStringArray(result, ", ");
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};

	public static final ExpressionTypeEnum SORTDESCSTRING = new ExpressionTypeEnum("SORTDESCSTRING", "enum.expressiontypeenum.sortdescstring", "enum.expressiontypeenum.sortdescstring.text", "enum.expressiontypeenum.sortdescstring.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments(), true)) return null;

			try {
				String[] result = new String[operands.length];
				for(int i = 0; i < operands.length; i++) {
					result[i] = getStringOperand(jexlContext, operands[i]);
				}

				Arrays.sort(result, Collections.reverseOrder());
				return StringUtils.toStringFromStringArray(result, ", ");
			} catch (Exception e) {
				//TODO;
				
			}
			return null;
		}
	};	
	
	public static final ExpressionTypeEnum LOWER = new ExpressionTypeEnum("LOWER", "enum.expressiontypeenum.lower", "enum.expressiontypeenum.lower.text", "enum.expressiontypeenum.lower.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			return getStringOperand(jexlContext, operands[0]).toLowerCase();
		}
	};
	
	public static final ExpressionTypeEnum UPPER = new ExpressionTypeEnum("UPPER", "enum.expressiontypeenum.upper", "enum.expressiontypeenum.upper.text", "enum.expressiontypeenum.upper.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			return getStringOperand(jexlContext, operands[0]).toUpperCase();
		}
	};
	
	public static final ExpressionTypeEnum CONCATENATE = new ExpressionTypeEnum("CONCAT", "enum.expressiontypeenum.concat", "enum.expressiontypeenum.concat.text", "enum.expressiontypeenum.concat.methodcall",VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			StringBuilder concat = new StringBuilder();
			for(Object operand: operands) {
				concat.append(getStringOperand(jexlContext, operand));
			}

			return concat.toString();
		}
	};

	public static final ExpressionTypeEnum LENGTH = new ExpressionTypeEnum("LEN", "enum.expressiontypeenum.len", "enum.expressiontypeenum.len.text", "enum.expressiontypeenum.len.methodcall", 1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return 0;

			return getStringOperand(jexlContext, operands[0]).length();
		}
	};

	public static final ExpressionTypeEnum REPLACE = new ExpressionTypeEnum("REPLACE", "enum.expressiontypeenum.replace", "enum.expressiontypeenum.replace.text", "enum.expressiontypeenum.replace.methodcall", 3) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";
			String source = getStringOperand(jexlContext, operands[0]);
			String replace = getStringOperand(jexlContext, operands[1]);
			String with = getStringOperand(jexlContext, operands[2]);

			return source.replace(replace, with);
		}
	};

	public static final ExpressionTypeEnum SUBSTRING = new ExpressionTypeEnum("SUBSTR", "enum.expressiontypeenum.substr", "enum.expressiontypeenum.substr.text", "enum.expressiontypeenum.substr.methodcall", 3) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			// the third argument here is optional
			if (!checkArgs(operands, 2)) return "";

			try {
				String source = getStringOperand(jexlContext, operands[0]);
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
	};

	public static final ExpressionTypeEnum CONTAINS = new ExpressionTypeEnum("CONTAINS", "enum.expressiontypeenum.contains", "enum.expressiontypeenum.contains.text", "enum.expressiontypeenum.contains.methodcall", 2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String findText = getStringOperand(jexlContext, operands[0]);
				String withinText = getStringOperand(jexlContext, operands[1]);

				return withinText.contains(findText);
			} catch (Exception e) {
				//TODO;
			}

			return false;
		}
	};

	public static final ExpressionTypeEnum SEARCH = new ExpressionTypeEnum("SEARCH", "enum.expressiontypeenum.search", "enum.expressiontypeenum.search.text", "enum.expressiontypeenum.search.methodcall", 3) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			// the third argument here is optional
			if (!checkArgs(operands, 2)) return "";

			try {
				String findText = getStringOperand(jexlContext, operands[0]);
				String withinText = getStringOperand(jexlContext, operands[1]);
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
	};

	public static final ExpressionTypeEnum TRIM = new ExpressionTypeEnum("TRIM", "enum.expressiontypeenum.trim", "enum.expressiontypeenum.trim.text", "enum.expressiontypeenum.trim.methodcall", 1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			return getStringOperand(jexlContext, operands[0]).trim();
		}
	};

	public static final ExpressionTypeEnum LEFT = new ExpressionTypeEnum("LEFT", "enum.expressiontypeenum.left", "enum.expressiontypeenum.left.text", "enum.expressiontypeenum.left.methodcall", 2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String source = getStringOperand(jexlContext, operands[0]);
				int index = getIntOperand(operands[1]);

				if(index > source.length()) index = source.length();

				return source.substring(0, index);
			} catch (Exception e) {
				//TODO;
			}

			return "";
		}
	};	

	public static final ExpressionTypeEnum RIGHT = new ExpressionTypeEnum("RIGHT", "enum.expressiontypeenum.right", "enum.expressiontypeenum.right.text", "enum.expressiontypeenum.right.methodcall", 2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				String source = getStringOperand(jexlContext, operands[0]);
				int index = source.length() - getIntOperand(operands[1]);
				if(index < 0) index = 0;

				return source.substring(index);
			} catch (Exception e) {
				//TODO;
			}

			return "";
		}
	};

	public static final ExpressionTypeEnum IF = new ExpressionTypeEnum("IF", "enum.expressiontypeenum.if", "enum.expressiontypeenum.if.text", "enum.expressiontypeenum.if.methodcall", 3) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum CASE = new ExpressionTypeEnum("CASE", "enum.expressiontypeenum.case", "enum.expressiontypeenum.case.text", "enum.expressiontypeenum.case.methodcall", VARARGS) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum ISBLANK = new ExpressionTypeEnum("ISBLANK", "enum.expressiontypeenum.isblank", "enum.expressiontypeenum.isblank.text", "enum.expressiontypeenum.isblank.methodcall", 1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (operands == null || operands.length < getNumberOfArguments()) return null;

			try {
				return StringUtils.isBlank(getStringOperand(jexlContext, operands[0]));
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};

	public static final ExpressionTypeEnum IN = new ExpressionTypeEnum("IN", "enum.expressiontypeenum.in", "enum.expressiontypeenum.in.text", "enum.expressiontypeenum.in.methodcall", 2) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};

	public static final ExpressionTypeEnum IFIN = new ExpressionTypeEnum("IFIN", "enum.expressiontypeenum.ifin", "enum.expressiontypeenum.ifin.text", "enum.expressiontypeenum.ifin.methodcall", 4) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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
	};


	public static final ExpressionTypeEnum NUMBER = new ExpressionTypeEnum("NUMBER", "enum.expressiontypeenum.number", "enum.expressiontypeenum.number.text", "enum.expressiontypeenum.number.methodcall",1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
			if (!checkArgs(operands, getNumberOfArguments())) return "";

			try {
				return getNumericOperand(operands[0]);
			} catch (Exception e) {
				//TODO;
			}

			return null;
		}
	};




	public static final ExpressionTypeEnum STRING = new ExpressionTypeEnum("STRING", "enum.expressiontypeenum.string", "enum.expressiontypeenum.string.text", "enum.expressiontypeenum.string.methodcall", 1) {
		public Object evaluate(JexlContext jexlContext, Object[] operands) {
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


	protected static final ArrayListMap<ExpressionTypeEnum> __allEnums;
    protected static final List<ExpressionTypeEnum> __numericExpressionEnums;
    protected static final List<ExpressionTypeEnum> __textualExpressionEnums;
    protected static final List<ExpressionTypeEnum> __otherExpressionEnums;

    protected static Map<String, List<ExpressionTypeEnum>> __allEnumsByDataType;
    private int _numberOfArguments;
    private String _description;
    private String _methodCall;
    
    static {
		__allEnums = new ArrayListMap<ExpressionTypeEnum>("getValue");
		__allEnums.add(ABS);
		__allEnums.add(AVERAGE);
		__allEnums.add(CEILING);
		__allEnums.add(DIVIDE);
		__allEnums.add(FLOOR);
		__allEnums.add(LN);
		__allEnums.add(LOG);
		__allEnums.add(MAX);
		__allEnums.add(MIN);
		__allEnums.add(NUMBER);
		__allEnums.add(POWER);
		__allEnums.add(PRODUCT);
		__allEnums.add(ROUND);
		__allEnums.add(SQRT);
		__allEnums.add(SUBTRACT);
		__allEnums.add(SUM);
		__allEnums.add(SORTASCSTRING);
		__allEnums.add(SORTDESCSTRING);
		
		__allEnums.add(CONCATENATE);
		__allEnums.add(LEFT);
		__allEnums.add(LENGTH);
		__allEnums.add(LOWER);
		__allEnums.add(REPLACE);
		__allEnums.add(RIGHT);
		__allEnums.add(SUBSTRING);
		__allEnums.add(CONTAINS);
		__allEnums.add(SEARCH);
		__allEnums.add(STRING);
		__allEnums.add(UPPER);
		__allEnums.add(TRIM);

		__allEnums.add(IF);
		__allEnums.add(CASE);
		__allEnums.add(ISBLANK);
		__allEnums.add(IFIN);
		__allEnums.add(IN);

		__numericExpressionEnums = new ArrayList<ExpressionTypeEnum>();
		__numericExpressionEnums.add(ABS);
		__numericExpressionEnums.add(AVERAGE);
		__numericExpressionEnums.add(CEILING);
		__numericExpressionEnums.add(DIVIDE);
		__numericExpressionEnums.add(FLOOR);
		__numericExpressionEnums.add(LN);
		__numericExpressionEnums.add(LOG);
		__numericExpressionEnums.add(MAX);
		__numericExpressionEnums.add(MIN);
		__numericExpressionEnums.add(NUMBER);
		__numericExpressionEnums.add(POWER);
		__numericExpressionEnums.add(PRODUCT);
		__numericExpressionEnums.add(ROUND);
		__numericExpressionEnums.add(SQRT);
		__numericExpressionEnums.add(SUBTRACT);
		__numericExpressionEnums.add(SUM);;

        __textualExpressionEnums = new ArrayList<ExpressionTypeEnum>();
		__textualExpressionEnums.add(CONCATENATE);
		__textualExpressionEnums.add(LEFT);
		__textualExpressionEnums.add(LENGTH);
		__textualExpressionEnums.add(LOWER);
		__textualExpressionEnums.add(REPLACE);
		__textualExpressionEnums.add(RIGHT);
		__textualExpressionEnums.add(STRING);
		__textualExpressionEnums.add(SUBSTRING);
		__textualExpressionEnums.add(CONTAINS);
		__textualExpressionEnums.add(SEARCH);
		__textualExpressionEnums.add(UPPER);
		__textualExpressionEnums.add(TRIM);
		__textualExpressionEnums.add(SORTASCSTRING);
		__textualExpressionEnums.add(SORTDESCSTRING);


		__otherExpressionEnums = new ArrayList<ExpressionTypeEnum>();
		__otherExpressionEnums.add(IF);
		__otherExpressionEnums.add(CASE);
		__otherExpressionEnums.add(ISBLANK);
		__otherExpressionEnums.add(IN);
		__otherExpressionEnums.add(IFIN);

		__allEnumsByDataType = new HashMap<String, List<ExpressionTypeEnum>>();
		__allEnumsByDataType.put(int.class.toString(), __numericExpressionEnums);
		__allEnumsByDataType.put(double.class.toString(), __numericExpressionEnums);
		__allEnumsByDataType.put(String.class.toString(), __textualExpressionEnums);
		__allEnumsByDataType.put(Object.class.toString(), __otherExpressionEnums);
	}

	private ExpressionTypeEnum(String val, String label, String description, String methodCall, int numberOfArguments) {
		super(val, label);
		_numberOfArguments = numberOfArguments;
		_description = description;
		_methodCall = methodCall;
	}

	public static ExpressionTypeEnum getEnum(String val) {
		return (ExpressionTypeEnum) getEnum(val, getAllEnums());
	}


    public static List getAllEnums() {
		return __allEnums;
	}

	public static List<ExpressionTypeEnum> getNumericEnums() {
		return __numericExpressionEnums;
	}

    public static List<ExpressionTypeEnum> getTextualEnums() {
	    return __textualExpressionEnums;
	}

    public static List<ExpressionTypeEnum> getOtherEnums() {
	    return __otherExpressionEnums;
	}

    public static List<ExpressionTypeEnum> getExpressionEnumsByDataType(String dataType) {
        return  (List)__allEnumsByDataType.get(dataType);
    }
    
    public String getDescription () { 
    	return _description;
    }
    
    public String getMethodCall () {
    	return _methodCall;
    }
    
    public int getNumberOfArguments() {
		return _numberOfArguments;
	}

	public Object evaluate(JexlContext jexlContext, Object[] operands) {
		return "";
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

	private static String getStringOperand(JexlContext jexlCtxt, Object operand) {
		if (operand == null) return "";

		if (operand instanceof String) {
			return (String) operand;
		}
		return StringUtils.toString(operand);
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
}


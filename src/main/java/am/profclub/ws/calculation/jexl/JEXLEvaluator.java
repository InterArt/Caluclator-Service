package am.profclub.ws.calculation.jexl;


import java.util.*;
import java.util.regex.*;

public abstract class JEXLEvaluator implements QueryConstants {

	private boolean _expectsArray;
	// DirectToFieldMapping Default Values
	public static final double DOUBLE_NULL = Double.NEGATIVE_INFINITY;
	public static final float FLOAT_NULL = Float.NEGATIVE_INFINITY;
	public static final int INT_NULL = Integer.MIN_VALUE;
	public static final long LONG_NULL = Long.MIN_VALUE;
	public static final short SHORT_NULL = Short.MIN_VALUE;
	public static final byte BYTE_NULL = Byte.MIN_VALUE;
	public static final char CHAR_NULL = '\0';


	public static JEXLEvaluator getEvaluator(String queryConstant){
		return (JEXLEvaluator) __evaluators.get(queryConstant);
	}


	private JEXLEvaluator(){
		_expectsArray = false;
	}

	private JEXLEvaluator(boolean expectsArray){
		_expectsArray = expectsArray;
	}

	public boolean getExpectsArray(){
		return _expectsArray;
	}

	public boolean getIsNullable() {
		return false;
	}

	public abstract boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal);



	private static Map __evaluators;
	private static JEXLEvaluator equal =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				return fieldVal.equals(testVal);
			}
		};
	private static JEXLEvaluator contains =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				return fieldVal.toString().indexOf(testVal.toString()) > -1;
			}
		};

	/**
	 * This will add always add implicit wildcard '%' at the end of testVal. Note this will not work as an "ends with" function.
	 */
	private static JEXLEvaluator like =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				String testRegEx = testVal.toString();
				StringBuilder sb = new StringBuilder();
				if (testVal.toString().startsWith("%")) sb.append(".*");
				String[] parts = testRegEx.split("%");

				for (String s : parts) {
					sb.append(Pattern.quote(s));
					sb.append(".*");
				}
				Pattern pattern = Pattern.compile(sb.toString());
				return pattern.matcher(fieldVal.toString()).matches();
			}
		};

	private static JEXLEvaluator cilike =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				return  like.evaluate(fieldVal.toString().toUpperCase(), testVal.toString().toUpperCase(), testRangeVal);
			}
		};

	private static JEXLEvaluator cicontains =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				if(fieldVal instanceof String && testVal instanceof String){
					fieldVal = fieldVal.toString().toLowerCase();
					testVal = testVal.toString().toLowerCase();
				}
				return fieldVal.toString().indexOf(testVal.toString()) > -1;
			}
		};

    private static JEXLEvaluator notcontains =  new JEXLEvaluator() {
		    public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
                return !contains.evaluate(fieldVal, testVal, testRangeVal);
		    }
		};
		
	private static JEXLEvaluator cinotcontains =  new JEXLEvaluator() {
		    public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
                return !cicontains.evaluate(fieldVal, testVal, testRangeVal);
		    }
		};

	private static JEXLEvaluator ciequal =  new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				if(fieldVal instanceof String && testVal instanceof String){
					fieldVal = fieldVal.toString().toLowerCase();
					testVal = testVal.toString().toLowerCase();
				}
				return fieldVal.equals(testVal);
			}
		};

	private static JEXLEvaluator between = new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null || testRangeVal == null) return false;
				if( ! (fieldVal instanceof Comparable)) return false;
					
				return ((Comparable)fieldVal).compareTo(testVal) >= 0 && ((Comparable)fieldVal).compareTo(testRangeVal) <=0;
			}
		};
	private static JEXLEvaluator cibetween = new JEXLEvaluator() {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null || testRangeVal == null) return false;
				if( ! (fieldVal instanceof Comparable)) return false;
				if(fieldVal instanceof String && testVal instanceof String && testRangeVal instanceof String){
					fieldVal = fieldVal.toString().toLowerCase();
					testVal = testVal.toString().toLowerCase();
					testRangeVal = testRangeVal.toString().toLowerCase();
				}
					
				return ((Comparable)fieldVal).compareTo(testVal) >= 0 && ((Comparable)fieldVal).compareTo(testRangeVal) <=0;
			}
		};


	private static JEXLEvaluator in =  new JEXLEvaluator(true) {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				if( ! (testVal instanceof Object[]) ) return false;
				Object[] testValArray = (Object[]) testVal;
				for( int i = 0; i < testValArray.length; i++){
					if( fieldVal.equals(testValArray[i])){
						return true;
					}
				}
				return false;
			}
		};

	private static JEXLEvaluator ciin =  new JEXLEvaluator(true) {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				if( fieldVal == null || testVal == null) return false;
				if( ! (testVal instanceof Object[]) ) return false;
				Object[] testValArray = (Object[]) testVal;
				
				if( fieldVal instanceof String ) {
					for( int i = 0; i < testValArray.length; i++){
						if(fieldVal.toString().toLowerCase().equals(testValArray[i].toString().toLowerCase())){
							return true;
						}
					}
				} else {
					for( int i = 0; i < testValArray.length; i++){
						if( fieldVal.equals(testValArray[i])){
							return true;
						}
					}
				}
				return false;
			}
	};
	
	static {
		__evaluators = new HashMap();
		__evaluators.put( LESSTHAN, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					if( fieldVal == null || testVal == null) return false;
					if( ! (fieldVal instanceof Comparable)) return false;
					return ((Comparable)fieldVal).compareTo(testVal) < 0;
				}
			});

		__evaluators.put( LESSTHANEQUAL, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					if( fieldVal == null || testVal == null) return false;
					if( ! (fieldVal instanceof Comparable)) return false;
					return ((Comparable)fieldVal).compareTo(testVal) <= 0;
				}
			});

		__evaluators.put( GREATERTHAN, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					if( fieldVal == null || testVal == null) return false;
					if( ! (fieldVal instanceof Comparable)) return false;
					return ((Comparable)fieldVal).compareTo(testVal) > 0;
				}
			});

		__evaluators.put( GREATERTHANEQUAL, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					if( fieldVal == null || testVal == null) return false;
					if( ! (fieldVal instanceof Comparable)) return false;
					return ((Comparable)fieldVal).compareTo(testVal) >= 0;
				}
			});

		
	
		
		__evaluators.put( EQUAL, equal);
		__evaluators.put( EXACT_TIME, equal);
		
		__evaluators.put( NOTEQUAL, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! equal.evaluate(fieldVal, testVal, testRangeVal);
				}
				
			});



		__evaluators.put( CONTAINS, contains);
		__evaluators.put( LIKE, like);

		
		__evaluators.put( CICONTAINS, cicontains);
		__evaluators.put( CILIKE, cilike);

        __evaluators.put( CINOTCONTAINS, cinotcontains);
		__evaluators.put( NOTCONTAINS, notcontains);


		__evaluators.put( CIEQUAL, ciequal);
		__evaluators.put( CINOTEQUAL, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! ciequal.evaluate(fieldVal, testVal, testRangeVal);
				}
				
			});

		__evaluators.put( SOUNDEX, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					if( fieldVal == null || testVal == null) return false;
					return soundex(fieldVal.toString()).equals( soundex(testVal.toString()));
				}
			});

	
		__evaluators.put( BETWEEN, between );
		__evaluators.put( NOTBETWEEN, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! between.evaluate(fieldVal, testVal, testRangeVal);
				}
				
			});

	
		__evaluators.put( CIBETWEEN, cibetween );
		__evaluators.put( CINOTBETWEEN, new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! cibetween.evaluate(fieldVal, testVal, testRangeVal);
				}
				
			});
		

		__evaluators.put( IN, in);
		__evaluators.put( NOTIN,  new JEXLEvaluator(true) {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! in.evaluate(fieldVal, testVal, testRangeVal);
				}
			});

		__evaluators.put( CIIN, ciin);
		__evaluators.put( CINOTIN,  new JEXLEvaluator(true) {
			public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
				return ! ciin.evaluate(fieldVal, testVal, testRangeVal);
			}
		});

		__evaluators.put( ISNULL,  new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return isNull(fieldVal);
				}
				
				public boolean getIsNullable() {
					return true;
				}

			});

		__evaluators.put( NOTNULL,  new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! isNull(fieldVal);
				}
				public boolean getIsNullable() {
					return true;
				}

			});

		__evaluators.put( ISBLANK,  new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return isBlank(fieldVal);
				}
				public boolean getIsNullable() {
					return true;
				}

			});

		__evaluators.put( NOTBLANK,  new JEXLEvaluator() {
				public boolean evaluate(Object fieldVal, Object testVal, Object testRangeVal){
					return ! isBlank(fieldVal);
				}
				public boolean getIsNullable() {
					return true;
				}

			});


	}

	public static boolean isBlank(Object object) {
		if( isNull(object) ) return true;
		if( object instanceof String ) return StringUtils.isBlank((String)object);
		return false;
	}

	public static boolean isNull(Object object) {
		if( object == null) return true;
		if( object instanceof Integer) return ((Integer)object).intValue() == INT_NULL;
		if( object instanceof Double) return ((Double)object).doubleValue() == DOUBLE_NULL;
		if( object instanceof Long) return ((Long)object).longValue() == LONG_NULL;
		if( object instanceof Float) return ((Float)object).floatValue() == FLOAT_NULL;
		if( object instanceof Character) return ((Character)object).charValue() == CHAR_NULL;
		if( object instanceof Short) return ((Short)object).shortValue() == SHORT_NULL;
		if( object instanceof Byte) return ((Byte)object).byteValue() == BYTE_NULL;
		return false;
	}

	public static final char[] SOUNDEX_MAPPINGS = {	'0','1','2','3','0','1','2','0','0','2','2','4','5','5','0','1','2','6','2','3','0','1','0','2','0','2'	};
	public static String soundex(String val) {

		String t = val.toUpperCase();

		StringBuffer buff = new StringBuffer();
		char c, prev = '?';

		for (int i=0; i < t.length() && buff.length() < 4 && (c = t.charAt(i)) != ','; i++) {
			
			if (c>='A' && c<='Z' && c != prev) {
				prev = c;
				
				// First char is installed unchanged, for sorting.
				if (i==0) {
					buff.append(c);
				} else {
					char m = SOUNDEX_MAPPINGS[c-'A'];
					if (m != '0')
						buff.append(m);
				}
			}
		}

		if (buff.length() == 0){
			return null;
		} else {
			for (int i = buff.length(); i < 4; i++) {
				buff.append('0');
			}

			return buff.toString();
		}
	}


}

package am.profclub.ws.calculation.eval;

import am.profclub.ws.calculation.util.*;

import java.util.regex.*;

/**
 * Created by admin on 7/3/17.
 */
public class ConditionalEvaluator {

/*

1.  EQUAL
2.  NOTEQUAL
3.  CONTAIN
4.  LIKE
5.  CILIKE
6.  CICONTAIN
7.  NOTCONTAIN
8.  CINOTCONTAIN
9.  CIEQUAL
10. BETWEEN
11. NOTBETWEEN
12. CIBETWEEN
13. CINOTBETWEEN
14. IN
15. NOTIN
16. CIIN
17. CINOTIN
19. BITWISEOR
18. BITWISEAND
20. LESSTHAN
21. LESSTHANEQUAL
22. GREATERTHAN
23. GREATERTHANEQUAL
24. CINOTEQUAL
25. SOUNDEX
26. ISNULL
27. NOTNULL
28. ISBLANK
29. NOTBLANK
 */

	public static boolean EQUAL(Object fieldVal, Object testVal) {
		if( fieldVal == null || testVal == null) return false;
		return fieldVal.equals(testVal);
	}

	public static boolean NOTEQUAL(Object fieldVal, Object testVal) {
		return !EQUAL(fieldVal, testVal);
	}

	public static boolean CONTAIN(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		return fieldVal.toString().indexOf(testVal.toString()) > -1;
	}

	public static boolean NOTCONTAIN(Object fieldVal, Object testVal){
		return !CONTAIN(fieldVal, testVal);
	}

	public static boolean LIKE(Object fieldVal, Object testVal){
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

	public static boolean CILIKE(Object fieldVal, Object testVal, Object testRangeVal){
		if( fieldVal == null || testVal == null) return false;
		return  LIKE(fieldVal.toString().toUpperCase(), testVal.toString().toUpperCase());
	}

	public static boolean CICONTAIN(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if(fieldVal instanceof String && testVal instanceof String){
			fieldVal = fieldVal.toString().toLowerCase();
			testVal = testVal.toString().toLowerCase();
		}
		return fieldVal.toString().indexOf(testVal.toString()) > -1;
	}

	public static boolean CINOTCONTAIN(Object fieldVal, Object testVal){
		return !CICONTAIN(fieldVal, testVal);
	}

	public static boolean CIEQUAL(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if(fieldVal instanceof String && testVal instanceof String){
			fieldVal = fieldVal.toString().toLowerCase();
			testVal = testVal.toString().toLowerCase();
		}
		return fieldVal.equals(testVal);
	}

	public static boolean CINOTEQUAL(Object fieldVal, Object testVal){
		return !CIEQUAL(fieldVal, testVal);
	}

	public static boolean BETWEEN(Object fieldVal, Object testVal, Object testRangeVal){
		if( fieldVal == null || testVal == null || testRangeVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;

		return ((Comparable)fieldVal).compareTo(testVal) >= 0 && ((Comparable)fieldVal).compareTo(testRangeVal) <=0;
	}

	public static boolean NOTBETWEEN(Object fieldVal, Object testVal, Object testRangeVal) {
		return !BETWEEN(fieldVal, testVal, testRangeVal);
	}

	public static boolean CIBETWEEN(Object fieldVal, Object testVal, Object testRangeVal){
		if( fieldVal == null || testVal == null || testRangeVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;
		if(fieldVal instanceof String && testVal instanceof String && testRangeVal instanceof String){
			fieldVal = fieldVal.toString().toLowerCase();
			testVal = testVal.toString().toLowerCase();
			testRangeVal = testRangeVal.toString().toLowerCase();
		}

		return ((Comparable)fieldVal).compareTo(testVal) >= 0 && ((Comparable)fieldVal).compareTo(testRangeVal) <=0;
	}

	public static boolean CINOTBETWEEN(Object fieldVal, Object testVal, Object testRangeVal) {
		return !CIBETWEEN(fieldVal, testVal, testRangeVal);
	}

	public static boolean IN(Object fieldVal, Object testVal){
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

	public static boolean NOTIN(Object fieldVal, Object testVal){
		return !IN(fieldVal, testVal);
	}

	public static boolean CIIN(Object fieldVal, Object testVal){
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

	public static boolean CINOTIN(Object fieldVal, Object testVal){
		return !CIIN(fieldVal, testVal);
	}

	public static boolean BITWISEAND(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( !(fieldVal instanceof LongBitMap) ||  !(testVal instanceof LongBitMap[]) ) return false;

		LongBitMap[] testValArray = (LongBitMap[]) testVal;
		long fieldValue = ((LongBitMap)fieldVal).getBitValue();
		long testValue = 0L;

		for(LongBitMap l : testValArray) {
			testValue |= l.getBitValue();
		}

		return ( (fieldValue & testValue) == testValue);
	}

	public static boolean BITWISEOR(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( !(fieldVal instanceof LongBitMap) ||  !(testVal instanceof LongBitMap[]) ) return false;

		LongBitMap[] testValArray = (LongBitMap[]) testVal;
		long fieldValue = ((LongBitMap)fieldVal).getBitValue();
		long testValue = 0L;

		for(LongBitMap l : testValArray) {
			testValue |= l.getBitValue();
		}

		return ( (fieldValue & testValue) > 0);
	}

	public static boolean LESSTHAN(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;
		return ((Comparable)fieldVal).compareTo(testVal) < 0;
	}

	public static boolean LESSTHANEQUAL(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;
		return ((Comparable)fieldVal).compareTo(testVal) <= 0;
	}

	public static boolean GREATERTHAN(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;
		return ((Comparable)fieldVal).compareTo(testVal) > 0;
	}

	public static boolean GREATERTHANEQUAL(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		if( ! (fieldVal instanceof Comparable)) return false;
		return ((Comparable)fieldVal).compareTo(testVal) >= 0;
	}

	public static boolean evaluate(Object fieldVal, Object testVal){
		if( fieldVal == null || testVal == null) return false;
		return soundex(fieldVal.toString()).equals( soundex(testVal.toString()));
	}

	public static boolean ISNULL(Object fieldVal) {
		return isNull(fieldVal);
	}

	public static boolean NOTNULL(Object fieldVal) {
		return !isNull(fieldVal);
	}

	public static boolean ISBLANK(Object fieldVal) {
		return isBlank(fieldVal);
	}

	public static boolean NOTBLANK(Object fieldVal) {
		return !isBlank(fieldVal);
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

	public static boolean isNull(Object object) {
		if( object == null) return true;
		if( object instanceof Integer) return ((Integer)object).intValue() == Constants.INT_NULL;
		if( object instanceof Double) return ((Double)object).doubleValue() == Constants.DOUBLE_NULL;
		if( object instanceof Long) return ((Long)object).longValue() == Constants.LONG_NULL;
		if( object instanceof Float) return ((Float)object).floatValue() == Constants.FLOAT_NULL;
		if( object instanceof Character) return ((Character)object).charValue() == Constants.CHAR_NULL;
		if( object instanceof Short) return ((Short)object).shortValue() == Constants.SHORT_NULL;
		if( object instanceof Byte) return ((Byte)object).byteValue() == Constants.BYTE_NULL;
		return false;
	}

	public static boolean isBlank(Object object) {
		if( isNull(object) ) return true;
		if( object instanceof String ) return StringHelper.isBlank((String)object);
		return false;
	}
}

package am.profclub.ws.calculation.jexl;

import java.io.*;
import java.net.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

import static am.profclub.ws.calculation.jexl.JEXLEvaluator.*;

public class StringUtils {

	public static final int INT_NULL = Integer.MIN_VALUE;

	public static int[] convertToIntArray(String[] vals) throws InvalidParameterException {
		try {
			if (isBlank(vals)) return null;
			int[] retVals = new int[vals.length];
			for (int i = 0; i < vals.length; i++) {
				retVals[i] = convertToInt(vals[i]);
			}
			return retVals;
		} catch (NumberFormatException x) {
			throw new InvalidParameterException(x.getMessage());
		}
	}

	public static boolean isBlank(String... val) {
		if (val == null || val.length == 0) return true;
		for (int i = 0; i < val.length; i++) {
			if (!isBlank(val[i])) return false;
		}
		return true;
	}

	public static boolean isBlank(Collection val) {
		return (val == null || val.size() == 0);
	}

	public static boolean stringsEqual(String val1, String val2) {
		if (val1 == null && val2 == null) {
			return true;
		}
		if (val1 == null || val2 == null) {
			return false;
		}
		return val1.equals(val2);
	}

	public static boolean stringsEqualIgnoreCase(String val1, String val2) {
		if (val1 == null && val2 == null) {
			return true;
		}
		if (val1 == null || val2 == null) {
			return false;
		}
		return val1.toLowerCase().equals(val2.toLowerCase());
	}

	public static String valueOf(Object o) {
		if (o == null) return null;
		if ("null".equals(o))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		if (o.toString().equals("0"))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		if (String.valueOf(INT_NULL).equals(String.valueOf(o)))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		return String.valueOf(o);
	}

	public static boolean isBlank(String val) {
		return val == null || val.length() == 0;
	}

	public static int convertToInt(String val) throws InvalidParameterException {
		try {
			if (isBlank(val)) return INT_NULL;
			return Integer.parseInt(val);
		} catch (NumberFormatException x) {
			throw new InvalidParameterException(x.getMessage());
		}

	}

	public static boolean[] convertToBooleanArray(String[] vals) throws InvalidParameterException {
		try {
			if (isBlank(vals)) return null;
			boolean[] retVals = new boolean[vals.length];
			for (int i = 0; i < vals.length; i++) {
				retVals[i] = convertToBoolean(vals[i]);
			}
			return retVals;
		} catch (NumberFormatException x) {
			throw new InvalidParameterException(x.getMessage());
		}

	}


	public static boolean convertToBoolean(String val) {
		if (isBlank(val)) return false;
		return val.equalsIgnoreCase("true") || val.equals("yes") || val.equals("on") || val.equals("1");
	}
	/**
	 * A faster split method that doesn't use regex.  It's about 10 times faster than the normal String.split().
	 */
	public static List<String> split(String val, char delimiter) {
		return split(val, delimiter, 0);
	}

	public static List<String> split(String val, char delimiter, int maxSplits) {
		List<String> list = new ArrayList<String>();
		if (val == null) return list;

		int start = 0;
		int count = 1;

		int index;
		while ((index = val.indexOf(delimiter, start)) >= 0 && (maxSplits == 0 || count < maxSplits)) {
			list.add(val.substring(start, index));
			start = index + 1;
			count++;
		}

		list.add(val.substring(start));

		return list;
	}

	public static String[] splitFast(String text, char delimiter) {
		int index = text.indexOf(delimiter);
		if (index == -1) {
			return new String[] {text};
		} else {
			return new String[] {
				text.substring(0, index),
				text.substring(index + 1)
			};
		}
	}

	public static boolean isBlank(Object val) {
		if (val instanceof Object[]) {
			Object[] vals = (Object[]) val;
			return isBlank(vals);
		} else if (val instanceof int[]) {
			int[] vals = (int[]) val;
			return isBlank(vals);
		}
		return val == null || val.toString().length() == 0;
	}

	public static List<String> split(String val, String delimiter) {
		return split(val, delimiter, 0);
	}

	public static List<String> split(String val, String delimiter, int maxSplits) {
		List<String> list = new ArrayList<String>();
		if (val == null) return list;

		int start = 0;
		int count = 1;

		int index;
		while ((index = val.indexOf(delimiter, start)) >= 0 && (maxSplits == 0 || count < maxSplits)) {
			list.add(val.substring(start, index));
			start = index + delimiter.length();
			count++;
		}

		list.add(val.substring(start));

		return list;
	}

	public static String toString(Object val) {
		if (val == null) return "";
		if (val instanceof Number)
			return toString((Number) val);
		if (val instanceof String[])
			return toStringFromStringArray((String[]) val, "");
		return val.toString();
	}

	public static String toStringFromStringArray(String[] sArr, String delimter) {
		if (sArr == null) return "";
		String s = "";

		for (int i = 0; i < sArr.length; i++) {
			if (i != 0) {
				s += delimter;
			}
			s += sArr[i];
		}

		return s;
	}

	public static String toString(Number val) {
		if (val == null || (val instanceof Integer && val.intValue() == INT_NULL)
			|| (val instanceof Double && val.doubleValue() == DOUBLE_NULL)) return "";

		if (val instanceof Integer) {
			return toString((Integer) val);
		} else if (val instanceof Double) {
			return toString((Double) val);
		}

		return null;

	}

	public static boolean objectsEqual(Object val1, Object val2) {
		if (val1 == null && val2 == null) {
			return true;
		}
		if (val1 == null || val2 == null) {
			return false;
		}

		if (val1.getClass().isArray() && val2.getClass().isArray()) {
			if (((Object[]) val1).length != ((Object[]) val2).length) return false;

			for (int i = 0; i < ((Object[]) val1).length; i++) {
				if (!objectsEqual(((Object[]) val1)[i], ((Object[]) val2)[i])) return false;
			}
			return true;
		} else {
			return val1.equals(val2);
		}
	}
}

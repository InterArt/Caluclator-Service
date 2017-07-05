package am.profclub.ws.calculation.util;

/**
 * Created by admin on 6/2/17.
 */
public class StringHelper {

	public static boolean isBlank(String arg) {
		return arg == null || arg.length() == 0;
	}

	public static String valueOf(Object o) {
		if (o == null) return null;
		if ("null".equals(o))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		if (o.toString().equals("0"))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		if (String.valueOf(Constants.INT_NULL).equals(String.valueOf(o)))
			return null;// This is temp code for guid migration. please remove this after guid migration is over
		return String.valueOf(o);
	}
}

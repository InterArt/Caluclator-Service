package am.profclub.ws.calculation.jexl;

import java.lang.reflect.*;
import java.util.*;

/**
 * Utility class for Converting and Organizing collections of objects into Maps
 **/
public class ArrayUtil {
	public static final int INT_NULL = Integer.MIN_VALUE;

	public static String getStringArrayAsString(String[] arr, String separator){
	    StringBuilder ret = new StringBuilder();
		int count = arr.length;

		for (int i = 0; i < count; i++) {
            ret.append(arr[i]);
            if( i < count-1) {
                ret.append(separator);
            }
        }

	    return ret.toString();
	}

	public static String getCollectionAsString(Collection collection, String separator) {
		StringBuilder result = new StringBuilder();
		for (Object object : collection) {
			if (result.length() > 0) result.append(separator);
			result.append(StringUtils.valueOf(object));
		}
		return result.toString();
	}

	public static String[] getStringArrayFromStringTokenizer(String val){
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(val);
		while (tokenizer.hasMoreElements()){
			list.add(tokenizer.nextToken());
		}
		return list.toArray(new String[list.size()]);
	}


	public static Map getMapFromDictionary(Dictionary dictionary) {
		Map result = new HashMap();
		if( dictionary == null || dictionary.size() == 0 ) return result;
		Enumeration keys = dictionary.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			result.put(key, dictionary.get(key));
		}

		return result;
	}

	public static int[] getIntArrayFromStringArray(String[] array){
		if( array == null || array.length == 0 ) return new int[]{};

		int[] vals = new int[array.length];
		for( int i = 0; i < array.length; i++){
			vals[i] = Integer.parseInt(array[i]);
		}

		return vals;
	}

	public static double[] getDoubleArrayFromStringArray(String[] array){
		if( array == null || array.length == 0 ) return new double[]{};

		double[] vals = new double[array.length];
		for( int i = 0; i < array.length; i++){
			vals[i] = Double.parseDouble(array[i]);
		}

		return vals;
	}

	public static List<Integer> getIntegerListFromStringArray(String[] array){
		if (array == null || array.length == 0) return new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>(array.length);

		for (String item : array) {
			result.add(Integer.parseInt(item));
		}

		return result;
	}

	public static Set<Integer> getIntegerSetFromStringArray(String[] array){
		if (array == null || array.length == 0) return new HashSet<Integer>();
		Set<Integer> result = new HashSet<Integer>(array.length);

		for (String item : array) {
			result.add(Integer.parseInt(item));
		}

		return result;
	}

	public static long[] getLongArrayFromStringArray(String[] array){
		if( array == null || array.length == 0 ) return new long[]{};

		long[] vals = new long[array.length];
		for( int i = 0; i < array.length; i++){
			vals[i] = Long.parseLong(array[i]);
		}

		return vals;
	}

	public static int[] getIntArrayFromCollection(Collection<Integer> collection) {
		int[] result = new int[collection.size()];
        int i = 0;
		for (Integer integer : collection) {
			result[i] = integer;
			i++;
		}
		return result;
	}

    public static int[] getIntArrayFromList(List<Integer> list) {
        int[] result = new int[list.size()];
        int i = 0;
        for (Integer integer : list)
            result[i++] = integer;
        return result;
    }


	public static String[] getStringArrayFromIntArray(int[] array) {
		if( array == null || array.length == 0 ) return new String[]{};

		String[] vals = new String[array.length];
		for( int i = 0; i < array.length; i++){
			vals[i] = array[i] + "";
		}

		return vals;
	}



	public static boolean contains(int[] theArray, int id) {
	    if( theArray == null || theArray.length==0 ) return false;

        for (int i = 0; i < theArray.length; i++) {
			if (id  == theArray[i]) return true;
		}

		return false;
	}

	public static boolean contains(String[] theArray, String key) {
		if( theArray == null || theArray.length==0 ) return false;

		for (int i = 0; i < theArray.length; i++) {
			if (key.equals(theArray[i])) return true;
		}

		return false;
	}

	public static <T> boolean contains(T[] arr, T val) {
		if (arr == null || arr.length == 0) return false;
		if (val == null) return false;

		for (int i=0; i<arr.length; i++) {
			if (val.equals(arr[i])) return true;
		}

		return false;
	}

	public static <T> int indexOf(T[] arr, T val) {
		if(arr == null) return -1;
		final int size = arr.length;
		if(size == 0) return -1;

		if (val == null) {
			for (int i = 0; i < size; i++) {
				if (arr[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (val.equals(arr[i])) return i;
			}
		}
		return -1;
	}

	public static <T> T[] removeElement(T[] arr, T val){
		final int index = indexOf(arr, val);
		if (index == -1) return arr.clone();

		final int size = arr.length;
		final T[] result = (T[])Array.newInstance(arr.getClass().getComponentType(), size - 1);

		System.arraycopy(arr, 0, result, 0, index);
		if (index < size - 1) {
            System.arraycopy(arr, index + 1, result, index, size - index - 1);
		}
        return result;
	}

	/**
	 * Finds the index location of the ID in the array
	 * @param theArray
	 * @param id
	 * @return the index if the ID is found, otherwise -1 is returned.
	 */
	public static int indexOf(int[] theArray, int id) {

        for (int i = 0; i < theArray.length; i++) {
			if (id  == theArray[i]) return i;
		}

		return -1;
	}

	/**
	 * Finds the index location of the ID in the array
	 * @param theArray
	 * @param id
	 * @return the index if the ID is found, otherwise -1 is returned.
	 */
	public static int indexOf(String[] theArray, String id) {

        for (int i = 0; i < theArray.length; i++) {
			if (id.equals(theArray[i])) return i;
		}

		return -1;
	}


	public static Object[] reverse(Object[] theArray) {
		Object[] tmp = new Object[theArray.length];

		for (int i = 1; i <= tmp.length; i++) {
			tmp[tmp.length - i] = theArray[i-1];
		}

		theArray= tmp;

		return theArray;
	}

	public static Collection reverse(Collection coll) {

		Object[] revered = reverse(coll.toArray(new Object[coll.size()]));

		Collection rev = new ArrayList(coll.size());
		Collections.addAll(rev, revered);

		coll.removeAll(coll);
		coll.addAll(rev);
		return rev;
	}

	public static Boolean[] wrapBooleanArray(boolean[] vals){
		if( vals == null) return null;
		Boolean[] ret = new Boolean[vals.length];
		for(int i = 0; i < vals.length; i++){
			ret[i] = Boolean.valueOf(vals[i]);
		}
		return ret;
	}


	public static Long[] wrapLongArray(long[] vals){
		if( vals == null) return null;
		Long[] ret = new Long[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Long.valueOf(vals[i]);
		}
		return ret;
	}

	public static Integer[] wrapIntArray(int[] vals){
		if( vals == null) return null;
		Integer[] ret = new Integer[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Integer.valueOf(vals[i]);
		}
		return ret;

	}

	public static Double[] wrapDoubleArray(double[] vals){
		if( vals == null) return null;
		Double[] ret = new Double[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Double.valueOf(vals[i]);
		}
		return ret;

	}


	public static Float[] wrapFloatArray(float[] vals){
		if( vals == null) return null;
		Float[] ret = new Float[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Float.valueOf(vals[i]);
		}
		return ret;
	}

	public static Character[] wrapCharArray(char[] vals){
		if( vals == null) return null;
		Character[] ret = new Character[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Character.valueOf(vals[i]);
		}
		return ret;

	}

	public static Byte[] wrapByteArray(byte[] vals){
		if( vals == null) return null;
		Byte[] ret = new Byte[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Byte.valueOf(vals[i]);
		}
		return ret;
	}


	public static Short[] wrapShortArray(short[] vals){
		if( vals == null) return null;
		Short[] ret = new Short[vals.length];
		for( int i = 0; i < vals.length; i++){
			ret[i] = Short.valueOf(vals[i]);
		}
		return ret;
	}



	public static String[] trimArray(String[] val){
		// Quick safety check
		if( val == null || val.length == 0 ) return new String[]{};

		// Remove blank elements
		int nonBlank = 0;
		boolean[] isBlank = new boolean[val.length];
		for( int i = 0; i < val.length; i++){
			if( val[i] != null && val[i].trim().length() > 0 ) {
				nonBlank++;
			} else {
				isBlank[i] = true;
			}
		}

		// Nothing found
		if( nonBlank == 0 ) return new String[]{};

		// Only copy non-blank elements and add those
		int idx = 0;
		String[] newVal = new String[nonBlank];
		for( int i = 0; i < val.length; i++){
			if( ! isBlank[i]){
				newVal[idx++] = val[i];
			}
		}
		return newVal;
	}


	public static int[] trimArray(int[] val){
		// Quick safety check
		if( val == null || val.length == 0 ) return new int[]{};

		// Remove blank elements
		int nonBlank = 0;
		boolean[] isBlank = new boolean[val.length];
		for( int i = 0; i < val.length; i++){
			if( val[i] != INT_NULL) {
				nonBlank++;
			} else {
				isBlank[i] = true;
			}
		}

		// Nothing found
		if( nonBlank == 0 ) return new int[]{};

		// Only copy non-blank elements and add those
		int idx = 0;
		int[] newVal = new int[nonBlank];
		for( int i = 0; i < val.length; i++){
			if( ! isBlank[i]) {
				newVal[idx++] = val[i];
			}
		}
		return newVal;
	}

	public static String[] mergeArrays(String[] val1, String[] val2){
		// Quick safety check
		if((val1 == null || val1.length == 0) &&
		   (val2 == null || val2.length == 0)) return new String[]{};

		Set s = new HashSet();
		if( val1 != null ) {
			Collections.addAll(s, val1);
		}

		if( val2 != null ) {
			Collections.addAll(s, val2);
		}

		int idx = 0;
		String[] ret = new String[s.size()];
		for(Iterator it = s.iterator(); it.hasNext(); ){
			ret[idx++] = (String) it.next();
		}

		return ret;
	}

	public static Object[] mergeArrays(Object[] val1, Object[] val2){
		// Quick safety check
		if((val1 == null || val1.length == 0) &&
		   (val2 == null || val2.length == 0)) return new Object[]{};

		Set s = new HashSet();
		if( val1 != null ) {
			Collections.addAll(s, val1);
		}

		if( val2 != null ) {
			Collections.addAll(s, val2);
		}

		Object[] ret = s.toArray(new Object[s.size()]);
		return ret;
	}

	public static int[] mergeArrays(int[] val1, int[] val2){
		// Quick safety check
		if((val1 == null || val1.length == 0) &&
		   (val2 == null || val2.length == 0)) return new int[]{};

		Set s = new HashSet();
		if( val1 != null ) {
			for(int i = 0; i < val1.length; i++){
				s.add(val1[i]);
			}
		}

		if( val2 != null ) {
			for(int i = 0; i < val2.length; i++){
				s.add(val2[i]);
			}
		}

		int idx = 0;
		int[] ret = new int[s.size()];
		for(Iterator it = s.iterator(); it.hasNext(); ){
			Integer ival = (Integer) it.next();
			ret[idx++] = ival;
		}

		return ret;
	}

	public static String[] subtrackArrays(String[] original, String[] minus){
		if (minus == null || minus.length ==0 || original == null || original.length == 0) return original;
		ArrayList<String> values = new ArrayList<String>();
		HashSet<String> minusvalues = new HashSet<String>(minus.length);
		minusvalues.addAll(Arrays.asList(minus));

		for (String s : original) {
			if (!minusvalues.contains(s)) values.add(s);
		}

		return values.toArray(new String[values.size()]);
	}


	/**
	 * @return <code>null</code> if there are no common values or if either parameter is empty or <code>null</code>. This also strips out blank values. That is, <code>null</code> and "" are not considered valid values in the arrays.
	 **/
	public static String[] intersectArrays(String[] val1, String[] val2){
		// Quick safety check
		if(val1 == null ||
		   val1.length == 0 ||
		   val2 == null ||
		   val2.length == 0) return null;

		Set s1 = new HashSet();
		for(int i = 0; i < val1.length; i++){
			if( ! StringUtils.isBlank(val1[i]) ){
				s1.add(val1[i]);
			}
		}

		Set s2 = new HashSet();
		for(int i = 0; i < val2.length; i++){
			if( ! StringUtils.isBlank(val2[i]) ){
				s2.add(val2[i]);
			}
		}

		s1.retainAll(s2);
		if( s1.size() == 0 ) return null;

		int idx = 0;
		String[] ret = new String[s1.size()];
		for(Iterator it = s1.iterator(); it.hasNext(); ){
			ret[idx++] = (String) it.next();
		}
		return ret;
	}

	public static String[] copyOfRange(String[] objIDs, int startIndex, int endIndex) {
		String[] batchIDs = new String[endIndex - startIndex];
		for (int j=0; j < batchIDs.length; j++ ){
			batchIDs[j] = objIDs[startIndex];
			startIndex++;
		}
		return batchIDs;
	}

	public static boolean isIn(String[] ids, String id) {
		if (ids == null || ids.length == 0) return false;

		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.stringsEqual(ids[i], id)) return true;
		}

		return false;
	}
}

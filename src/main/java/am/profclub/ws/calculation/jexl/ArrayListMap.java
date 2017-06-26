package am.profclub.ws.calculation.jexl;

import org.apache.log4j.*;

import java.lang.reflect.*;
import java.util.*;

/**
 * ArrayListMap is a subclass of java.util.ArrayList that also indexes its entries in a Map. These entries can be accessed by getValue() and
 * getValues(). Note that this Map allows duplicates entries and stores them in a Object->List Map.
 * Because this class is used by many Toplink-mapped classes which uses a no-arg constructor, it supplies a default Map key methodname of "getName()".
 */
public class ArrayListMap<E> extends ArrayList<E> {

	private static final Logger log = Logger.getLogger(ArrayListMap.class);
	
	public static final String DEFAULT_KEY_METHODNAME = "getName";

	protected Map<Object, List<E>> _map;
	protected String _methodName;
	protected transient Method _method;

	public ArrayListMap() {
		_map = new HashMap();
		_methodName = getDefaultKeyMethodName();
	}

	public ArrayListMap(int val) {
		super(val);
		_map = new HashMap();
		_methodName = getDefaultKeyMethodName();
	}

	public ArrayListMap(Collection c) {
		super(c);
		_map = new HashMap();
		_methodName = getDefaultKeyMethodName();
		for( Iterator it = c.iterator(); it.hasNext();){
			addToMap(it.next());
		}
	}

	public ArrayListMap(String methodName) {
		_map = new HashMap();
		_methodName = methodName;
	}
	
	public ArrayListMap(String methodName, Collection c) {
		super(c);
		_map = new HashMap();
		_methodName = methodName;
		for( Iterator it = c.iterator(); it.hasNext();){
			addToMap(it.next());
		}
	}

	protected void initMethod(Object o){
		try {
			_method = o.getClass().getMethod(_methodName);
		} catch(Exception x){
			log.debug(x);
		}
	}

	protected void addToMap(Object o){
		if( o == null ) return;
		if( _method == null || !_method.getClass().equals(o.getClass())) initMethod(o);
		try {
			Object key = _method.invoke(o);
			List list = _map.get(key );
			if( list == null){
				list = new ArrayList();
				_map.put(key, list);
			}
			list.add(o);
		} catch (Exception x){
			log.debug(x);
		}
	}

	protected void removeFromMap(Object o){
		if( o == null ) return;
		if( _method == null) initMethod(o);
		try {
			Object key = _method.invoke(o);
			List list = _map.get(key);
			if( list == null ) return;

			list.remove(o);
			if( list.size() == 0){
				_map.remove(key);
			}
		} catch (Exception x){
			log.debug(x);
		}
	}

	/**
	 * This was added so that non-default method names can be used with external tools that require a no-arg constructor, e.g. TopLink or JSP Tags. 
	 * Overriding this method will allow this class to be subclassed more easily.
	 **/
	protected String getDefaultKeyMethodName(){
		return DEFAULT_KEY_METHODNAME;
	}

	/**
	 * Should be called to re-synchronize an object with its key
	 * @param o is the stale key
	 * @param n is the new key
	 **/
	public synchronized void reIndex(Object o, Object n){
		List obj = _map.get(o);
		if( obj != null){
			_map.remove(o);
			_map.put(n, obj);
		}
	}

	/**
	 * Extract 1 object in a List to move to a new List under a new index
	 * @param obj is the object to reindex
	 * @param o is the stale key
	 * @param n is the new key
	 **/
	public synchronized void reIndex(Object obj, Object o, Object n){
		List objList = _map.get(o);
		if( objList != null){
		    Object removed = objList.remove(obj);

			// If it wasn't there to begin with, then don't worry about it
			// This was added because objects were being doubly added to the target collection
			if( removed == null ) return; 

			List toList = _map.get(n);
			if( toList == null ) {
				toList = new ArrayList();
				_map.put(n, toList);
			}

			toList.add(obj);
		}
	}

	/**
	 * @param key The index returned by the methodName provided in the constructor
	 * @return The first item in the List of objects. Or <code>null</code> if none are defined
	 **/
	public Object getValue(Object key){
		List list = getValues(key);
		if( list == null || list.size() == 0 ) return null;
		return list.get(0);
	}


	/**
	 * @param key The index returned by the methodName provided in the constructor
	 * @return a List containing all values defined for this object. Or <code>null</code> if none are defined.
	 **/
	public List<E> getValues(Object key){
		return _map.get(key);
	}

	public Set getMapKeySet(){
		return _map.keySet();
	}

	//////////////////////////////////////////////////
	// List interface overrides
	public void add(int index, E element) {
		super.add(index, element);
		addToMap(element);
	}

	public boolean add(E o) {
		boolean val = super.add(o);
		addToMap(o);
		return val;
	}

	public  boolean addAll(Collection c) {
		boolean val = super.addAll(c);
		for(Iterator it = c.iterator(); it.hasNext();){
			addToMap(it.next());
		}
		return val;
	}

	public  boolean addAll(int index, Collection c) {
		boolean val = 	super.addAll(index, c);
		for(Iterator it = c.iterator(); it.hasNext();){
			addToMap(it.next());
		}
		return val;
	}


	public boolean remove(Object o) {
		boolean val = super.remove(o);
		removeFromMap(o);
		return val;
	}

	public  boolean removeAll(Collection c) {
		boolean val = super.removeAll(c);
		for(Iterator it = c.iterator(); it.hasNext();){
			removeFromMap(it.next());
		}
		return val;
	}


	public void clear() {
		super.clear();
		_map.clear();
	}

	protected void clearMap() {
		_map.clear();
	}

	public E remove(int index) {
		E o = super.remove(index);
		removeFromMap(o);
		return o;
	}



	public boolean retainAll(Collection c){
		boolean val = super.retainAll(c);
		clearMap();
		for(Iterator it = c.iterator(); it.hasNext();){
			addToMap(it.next());
		}
		return val;
	}

	public E set(int index, E element){
		E o = super.set(index, element);
		removeFromMap(o);
		addToMap(element);
		return o;
	}

	public Map getAsMap(){
		return _map;
	}

}

package am.profclub.ws.calculation.util;


import java.io.*;
import java.util.*;

/**
 * LongBitMap is a utility data type for use in TopLink-mapped classes that allow a 1-M relationship to be stored in a single field using bitwise operations. 
 * It is an immutable class. add() and remove() operations return new instances of LongBitMap that should be used for storage. 
 *
 * For simplicity and efficiency, LongBitMap is limitiedallows for up to 64 unique objects to be stored in a single field.
 **/
public class LongBitMap implements Serializable, Comparable, Bitmappable {

	public static final int MAX_SIZE = 64;
	public static final LongBitMap ALL = new LongBitMap(0xFFFFFFFFFFFFFFFFL);

	private long _bitmap;

	public LongBitMap() {
		this(0L);
	}

	public LongBitMap(long val){
		if( val != Constants.LONG_NULL){
			_bitmap = val;
		} else {
			_bitmap = 0L;
		}
	}

	public LongBitMap(Long val){
		if( val != null){
			_bitmap = val;
		} else {
			_bitmap = 0L;
		}
	}

	public LongBitMap(Collection<? extends Bitmappable> values) {
		_bitmap = 0L;
		for (Bitmappable value : values) {
			_bitmap |= value.getBitValue();
		}
	}

	public LongBitMap add(Bitmappable bm){
		if( bm == null) return this;
		return new LongBitMap(_bitmap |  bm.getBitValueAsLong());
	}

	public LongBitMap remove(Bitmappable bm){
		if( bm == null) return this;
		return remove(bm.getBitValueAsLong());
	}

	public LongBitMap remove(long val){
		long tmp = ~(val);
		return new LongBitMap(_bitmap & tmp);
	}

	public LongBitMap xOr(Bitmappable bm){
		if( bm == null) return this;
		return new LongBitMap(_bitmap ^ bm.getBitValueAsLong());
	}

	public boolean binaryOr(Bitmappable bm){
		return bm != null && (_bitmap & bm.getBitValueAsLong()) > 0;
	}

	public boolean binaryAnd(Bitmappable bm) {
		return bm != null && (_bitmap & bm.getBitValueAsLong()) == bm.getBitValueAsLong();
	}

	public boolean binaryAnd(long val) {
		return (_bitmap & val) == val;
	}


	public boolean binaryOr(Bitmappable[] bm){
		if (bm == null || bm.length == 0) return false;
		for( int i = 0; i < bm.length; i++){
			if( (_bitmap & bm[i].getBitValueAsLong()) == 0) return false;
		}
		return true;
	}

	public boolean binaryAnd(Bitmappable[] bm) {
		if (bm == null || bm.length == 0) return false;
		for( int i = 0; i < bm.length; i++){
			if( (_bitmap & bm[i].getBitValueAsLong()) != bm[i].getBitValueAsLong()) return false;
		}
		return true;
	}

	public int getSize() {
		return getSize(MAX_SIZE);
	}

	public int getSize(int maxSize) {
		int count = Long.bitCount(_bitmap);
		return Math.min(count, maxSize);
	}

    public long getBitValueAsLong(){
		return _bitmap;
	}

	public Long getBitValue() {
		return _bitmap;
	}

	public boolean equals(Object o){
		if(o instanceof LongBitMap)
		{
			return _bitmap == ((LongBitMap)o)._bitmap;
		}
		else
		{
			return false;
		}
	}

	public int compareTo(Object o){
		LongBitMap lbm = (LongBitMap) o;
		if( _bitmap < (lbm._bitmap)) return 1;
		if( _bitmap > (lbm._bitmap)) return -1;
		return 0;
	}
}

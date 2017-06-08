package am.profclub.ws.calculation.converter;

/**
 * Created by admin on 6/2/17.
 */
public interface Converter {

	public String convert(String expression);

	public String convert(String domain, String expression);
}

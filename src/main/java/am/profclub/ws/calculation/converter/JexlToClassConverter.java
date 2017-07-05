package am.profclub.ws.calculation.converter;

import org.springframework.stereotype.*;

/**
 * Created by admin on 6/2/17.
 */
@Component
public class JexlToClassConverter implements Converter{

	private static final String basePackage = "am.profclub.calculation";

	@Override
	public String convert(String expression) {
		return convert("NULL", expression);
	}

	@Override
	public String convert(String domain, String expression) {
		return null;
	}
}

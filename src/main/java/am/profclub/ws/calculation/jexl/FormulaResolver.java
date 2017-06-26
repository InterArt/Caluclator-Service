package am.profclub.ws.calculation.jexl;

import java.util.*;

public interface FormulaResolver {
	public String resolveField(String objectName, String fieldName) throws RuntimeException;
	public String resolveWildcard(String wildcard) throws RuntimeException;
	public String resolveFunctionCall(String functionName, List<String> parameters) throws RuntimeException;
}

package am.profclub.ws.calculation.parser;

import java.util.*;

public interface FormulaResolver {
	public String resolveField(String objectName, String fieldName) throws FormulaException;
	public String resolveWildcard(String wildcard) throws FormulaException;
	public String resolveFunctionCall(String functionName, List<String> parameters) throws FormulaException;
}

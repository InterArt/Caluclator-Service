package am.profclub.ws.calculation.parser;

import java.util.*;
import java.util.regex.*;

/**
 * Created by admin on 7/6/17.
 */
public class FormulaResolverImpl implements FormulaResolver {

	private int parameterCount = 0;

	public String resolveField(String objectName, String fieldName) throws FormulaException {
		if (fieldName == null || fieldName.length() == 0) return "";

		try {
			String expression;
			if (objectName == null) {
				expression = "o";
			} else if (objectName.startsWith("$$")) {
				expression = objectName.substring(2);
			} else {
				expression = objectName;
			}

			return expression;

		} catch (Exception e) {
			throw new UnknownFieldException(fieldName);
		}
	}

	public String resolveWildcard(String wildcard) throws FormulaException {
		throw new RuntimeException("Not implemented Yet!");
	}

	public String resolveFunctionCall(String functionName, List<String> parameters) throws FormulaException {
		Expression expression = new Expression();
		StringBuilder parameterNames = new StringBuilder();
		for (String parameter : parameters) {
			String parameterName = "_p" + (parameterCount++);

			if (parameterNames.length() > 0) parameterNames.append(",");
			parameterNames.append(parameterName);

			// SUB
			expression.sub.add(new Expression(parameterName, parameter));
		}

		expression.functionName = functionName;
		expression.parameters = parameterNames.toString();

		return expression.toString();
	}
}

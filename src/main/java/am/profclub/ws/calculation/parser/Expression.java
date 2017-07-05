package am.profclub.ws.calculation.parser;

import java.util.*;

/**
 * Created by admin on 7/6/17.
 */
public class Expression {

	String functionName;
	String parameters;
	List<Expression> sub;

	public Expression() {
		sub = new ArrayList<>();
	}

	public Expression(String functionName, String parameters) {
		this.functionName = functionName;
		this.parameters = parameters;
	}


	@Override
	public String toString() {
		return "Expression{" +
			"functionName='" + functionName + '\'' +
			", parameters='" + parameters + '\'' +
			", sub=" + sub +
			'}';
	}
}

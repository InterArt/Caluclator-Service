package am.profclub.ws.calculation.parser;

import java.io.*;
import java.util.*;

/**
 * Created by admin on 7/4/17.
 */
public class ExpressionParserTest {



	public static void main(String[] args) {
//		ExpressionParser parser = new ExpressionParser();
//		List list = parser.getContents("IF(a=1, 1, 2)");
//		System.out.println(list);
//
//		list = parser.getContents("IF(a=2, IF(a=1, 1, 2), 3)");
//		System.out.println(list);



//		Stack<ExpressionParser.Expression> stack = ExpressionParser.parse("IF(a=1, 1, 2)", null);
//		print(stack);
//
//		stack = ExpressionParser.parse("IF(a==2, IF(a==1, 1, 2), 3)", null);
//		print(stack);

		String expression;
		try {
			FormulaResolver formulaResolver = new FormulaResolverImpl();
			FormulaParser parser = new FormulaParser(new StringReader("CONCAT(1, 3)"));
			parser.setResolver(formulaResolver);
			expression = parser.parseFormula();


			System.out.println(expression);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void print(Stack<ExpressionParser.Expression> expressions) {
		Iterator<ExpressionParser.Expression> exp = expressions.iterator();
		while (exp.hasNext()) {
			ExpressionParser.Expression expression = exp.next();
			System.out.println(expression);
		}
	}
}

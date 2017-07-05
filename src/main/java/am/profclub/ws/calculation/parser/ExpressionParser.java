package am.profclub.ws.calculation.parser;

import am.profclub.ws.calculation.eval.*;
import am.profclub.ws.calculation.jexl.*;

import java.util.*;

/**
 * Created by admin on 7/4/17.
 */
public class ExpressionParser {

	public static Stack<Expression> parse(String expression, Stack<Expression> result) {
		if (result == null) {
			result = new Stack<>();
		}
		char[] charArray = expression.toCharArray();
		StringBuilder expressionBuilder = new StringBuilder();
		int index = 0;//function index
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			expressionBuilder.append(c);

			String exp = expressionBuilder.toString();
			if (ExpressionType.functions().contains(exp)) {
				//expression start with function
				int startPosition;
				if ((startPosition = getStartPosition(expression, i + 1)) != -1) {
					//load content
					Expression _exp = loadExpression(expression, exp, startPosition);
					result.add(_exp);
					expressionBuilder = new StringBuilder();
					index++;
				} else {
					continue;
				}
			}
		}

		return result;
	}

	public static Expression loadExpression(String expression, String function, int position) {
		Expression _exp = new Expression();
		_exp.function = function;
		char c;
		StringBuilder original = new StringBuilder(function).append("(");
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (int i = position; i < expression.length(); i++) {
			c = expression.charAt(i);
			original.append(c);

			if (c == '(') {//we have nested function
				//try to find start point of function
				int startPoint = findStartPoint(expression, i - 1, position);
				Stack<Expression> nestedExp = parse(expression.substring(startPoint), null);
				_exp.addOperands(index, nestedExp);
				_exp.hasSubExpression = true;
				index++;

				Iterator<Expression> it = nestedExp.iterator();
				while (it.hasNext()) {
					i += it.next().originalExpression.length();
				}

			} else if (c == ')') {
				_exp.originalExpression = original.toString();
				String build = builder.toString();

				if (_exp.hasSubExpression) {
					String array[] = build.split(",");
					for (int j = 0; j < array.length; j++) {
						if (StringUtils.isBlank(array[j])) continue;

						_exp.addOperands(index++, array[j]);
					}
				} else {
					_exp.addAllOperands(build.split(","));
				}
				break;
			} else {
				builder.append(c);
			}
		}

		return _exp;
	}

	public static int findStartPoint(String exp, int startPoint, int minPoint) {
		StringBuilder b = new StringBuilder();
		char c;
		for (int i = startPoint; i > minPoint; i--) {
			c = exp.charAt(i);
			b.append(c);
			String s = b.reverse().toString();
			if (ExpressionType.functions().contains(s)) {
				return i;
			}
		}
		return -1;
	}

	public static int getStartPosition(String str, int position) {
		for (int i = position; i < str.length(); i++) {
			if (str.charAt(i) == ' ') continue;
			if (str.charAt(i) == '(') return i+1;
			else return -1;
		}

		return -1;
	}

	public static List<String> getContents(String str) {
		Stack<Character> stack = new Stack<Character>();
		List<String> contents = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		char c;
		for(int i=0; i < str.length(); i++) {
			c = str.charAt(i);

			if (c == '(') {
				stack.push(c);
			} else if(c == ')') {
				if (stack.empty()) {
					throw new RuntimeException("mismatch of parenthesis");
				} else if (stack.peek() == '(') {
					stack.pop();
					contents.add(builder.toString());
					builder = new StringBuilder();
				} else
					throw new RuntimeException("mismatch of parenthesis");
			} else {
				builder.append(c);
			}
		}
		if (stack.empty()) {
		 	return contents;
		}

		throw new RuntimeException("mismatch of parenthesis");
	}

	public static class Expression {
		String function;
		Object[] operands;
		boolean hasSubExpression;
		String originalExpression;

		@Override
		public String toString() {
			return "Expression{" +
				"function='" + function + '\'' +
				", operands=" + Arrays.toString(operands) +
				", hasSubExpression=" + hasSubExpression +
				", originalExpression='" + originalExpression + '\'' +
				'}';
		}

		public void addOperands(int index, Object obj) {
			if (operands == null) {
				operands = new Object[index+1];
				operands[index] = obj;
			} else {
				int length = operands.length;
				Object[] tmp = new Object[length + 1];
				int i = 0;
				for (Object o : operands) {
					tmp[i] = o;
					i++;
				}
				tmp[index] = obj;
				operands = tmp;
			}
		}

		public void addAllOperands(String[] split) {
			if (operands == null) {
				operands = split;
			} else {
				int length = operands.length;
				Object[] tmp = new Object[length + 1];
				int i = 0;
				for (Object o : operands) {
					tmp[i] = o;
					i++;
				}

				for (String s : split) {
					tmp[i] = s;
					i++;
				}

				operands = tmp;
			}
		}
	}
}

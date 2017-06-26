package am.profclub.ws.calculation.jexl;

import java.util.*;

/**
 * Allows a JEXL expression to be built programmatically.
 */
public class JEXLExpressionHolder {

	// Holds a list of OR groups that contain lists of clauses joined with AND.
	private List<List<String>> _expressions;

	public JEXLExpressionHolder() {
		_expressions = new ArrayList();
		_expressions.add(new ArrayList());
	}

	public void addAnd(String expression) {
		if (expression == null || expression.length() == 0) return;
		currentExpression().add(expression);
	}

	public void addOr(JEXLExpressionHolder expressionHolder) {
		String expression = expressionHolder.getJEXLExpression();
		if (expression == null || expression.length() == 0) return;

		_expressions.add(new ArrayList());
		addAnd(expression);
	}

	private List<String> currentExpression() {
		return _expressions.get(_expressions.size() - 1);
	}

	public String getJEXLExpression() {
		StringBuilder sb = new StringBuilder();
		for (Iterator ors = _expressions.iterator(); ors.hasNext();) {
			List list = (List) ors.next();

			if (list.isEmpty()) {
				sb.append("true");

			} else {
				sb.append("(");
				for (Iterator ands = list.iterator(); ands.hasNext();) {
					String expression = (String) ands.next();
					sb.append("(");
					sb.append(expression);
					sb.append(")");

					if (ands.hasNext()) {
						sb.append(" && ");
					}
				}
				sb.append(")");
			}

			if (ors.hasNext()) {
				sb.append(" || ");
			}
		}

		return sb.toString();
	}
}

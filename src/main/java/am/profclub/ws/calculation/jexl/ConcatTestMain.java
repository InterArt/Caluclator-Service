package am.profclub.ws.calculation.jexl;

import org.apache.commons.jexl3.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Created by admin on 6/23/17.
 */
public class ConcatTestMain {

	public static void main(String[] args) {
		String jext = "h.eval(o,'Concat','_p6,_p7')";
		JexlExpression expression = Jexl3Helper.createExpression(jext);

		Map<String, String> subexpressions = new HashMap<String, String>();
		subexpressions.put("_p5" , "\"1\"");
		subexpressions.put("_p6" , "h.eval(o,'IF','_p0,_p1,_p2',true, a)");
		subexpressions.put("_p7" , "h.eval(o,'IF','_p3,_p4,_p5',true, a)");
		subexpressions.put("_p1" , "\"1\"");
		subexpressions.put("_p2" , "\"2\"");
		subexpressions.put("_p3" , "\"2 == 1\"");
		subexpressions.put("_p4" , "\"2\"");
		subexpressions.put("_p0" , "\"1 == 1\"");

		Map<String, JexlExpression> jexlSubexpressions = new HashMap<String, JexlExpression>();
		// Also, compile any sub expressions
		for (Map.Entry<String, String> entry : subexpressions.entrySet()) {
			String name = entry.getKey();
			JexlExpression e = Jexl3Helper.createExpression(entry.getValue());
			jexlSubexpressions.put(name, e);
		}

		JexlContext context = new MapContext();
		context.set("h", JEXLHelper.getInstance());
		context.set("a", jexlSubexpressions);
		context.set("o", context);

		Object val = expression.evaluate(context);
		System.out.println(val);

	}


}

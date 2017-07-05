package am.profclub.ws.calculation.converter;

/**
 * Created by admin on 6/2/17.
 */
public class ConditionToMethodConverter {

	static final String expression_prefix = "#";
	public void buildExpressionQueue(String expression) {
		char[] array = expression.toCharArray();
		for (int i = 0; i < array.length; i++) {

		}
	}
}


/*
	from expression build map

	ex1:
	concat(IF (a=1, 1, 2), "b")

	Map {
		concat  -> $exp1
		if 		-> $exp2
		$exp1 -> [$exp2, "b"]
		$exp2   -> [a=1, 1, 2]
	}

	ex2:
	IF (x, IF (y, 1, 2), 3)
	Map {
		if  -> $exp1
		if 	-> $exp2
		$exp1  -> 	[x, $exp2, 3]
		$exp2 -> 	[y, 1, 2]
	}
 */
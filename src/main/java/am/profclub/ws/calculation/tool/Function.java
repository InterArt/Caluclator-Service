package am.profclub.ws.calculation.tool;


/**
 * This interface represents a simply mathematical function {@code y = f(x)}
 * that maps double to doubles.
 */
public interface Function {
	/**
	 * Compute a value {@code y=f(x)} of an dependent variable y from an
	 * independent variable x
	 *
	 * @param x
	 *           the input value
	 * @return the result of a mathematical function f(x)
	 */
	double f(double x);
}

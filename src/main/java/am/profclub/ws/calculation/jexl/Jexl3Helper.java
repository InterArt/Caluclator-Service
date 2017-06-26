package am.profclub.ws.calculation.jexl;

import org.apache.commons.jexl3.*;

import java.util.concurrent.*;

/**
 * Created by levon on 5/11/17.
 */
public final class Jexl3Helper {
	private static volatile JexlEngine jexlEngine = null;
	// Since the parsing of expression may require a lot of memory we shouldn't allow the access
	// to createExpreesion functionality from many threads.
	// We initilize the Semaphore with alowing access at most for 20 threads simulteniosly
	// We passing the second parameter as 'true' in order to don't have some threades waiting for long period
	private static Semaphore semaphore =  new Semaphore(20, true);

	public static JexlEngine getEngine() {
		if (jexlEngine == null) {
			synchronized (Jexl3Helper.class) {
				if (jexlEngine == null) {
					JexlEngine tmp = (new JexlBuilder()).strict(false).silent(false).create();
					jexlEngine = tmp;
				}
			}
		}
		return jexlEngine;
	}

	public static final JexlExpression createExpression(String expression) {
		try {
			semaphore.acquire();
			return getEngine().createExpression(expression);
		} catch (InterruptedException e) {
			//if the current thread is interupted there is nothing to do with that.
			throw new RuntimeException(e);
		} catch (JexlException e) {
			// this method should handle some legacy cases, espesially issues with backslashes
			if (e instanceof JexlException.Tokenization) {
				return handleSpecificExceptions((JexlException.Tokenization)e);
			}
			throw e;
		} finally {
			semaphore.release();
		}
	}

	private static JexlExpression handleSpecificExceptions(JexlException.Tokenization tokenException) {
		String detail = tokenException.getDetail();
		if (detail.contains("\\")) {
			return getEngine().createExpression(detail.replace("\\", "\\\\"));
		}
		throw tokenException;
	}
}

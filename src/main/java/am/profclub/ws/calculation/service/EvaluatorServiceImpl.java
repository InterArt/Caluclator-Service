package am.profclub.ws.calculation.service;

import am.profclub.ws.calculation.dto.request.*;
import am.profclub.ws.calculation.tool.*;
import org.apache.log4j.*;
import org.springframework.stereotype.*;

import javax.tools.*;
import java.io.*;
import java.util.*;

/**
 * Created by admin on 7/3/17.
 */
@Service
public class EvaluatorServiceImpl implements EvaluatorService {

	Logger logger = Logger.getLogger(EvaluatorServiceImpl.class);

	// Create a CharSequenceCompiler instance which is used to compile
	// expressions into Java classes which are then used to create the XY plots.
	// The -target 1.5 options are simply an example of how to pass javac
	// compiler
	// options (the generated source in this example is Java 1.5 compatible.)
	private final CharSequenceCompiler<CalculatedField> compiler = new CharSequenceCompiler<CalculatedField>(
		getClass().getClassLoader(), Arrays.asList(new String[] { "-target", "1.7" }));
	// for unique class names
	private int classNameSuffix = 0;
	// package name; a random number is appended
	private static final String PACKAGE_NAME = "am.profclub.ws.calculation.runtime";
	// for secure package name
	private static final Random random = new Random();
	// the Java source template
	private String template;


	@Override
	public void check(String domain, String fieldName) {

	}

	@Override
	public Object eval(String domain, String fieldName, ParamListDto paramListDto) {
		return null;
	}

	@Override
	public InputStream getGeneratedClass(String domain, String fieldName) {
		return null;
	}

	@Override
	public void toClass(String domain, JexlParamDto jexlParamDto) {

	}




	/**
	 * Return the Plotter function Java source, substituting the given package
	 * name, class name, and double expression
	 *
	 * @param packageName
	 *           a valid Java package name
	 * @param className
	 *           a valid Java class name
	 * @param expression
	 *           text for a double expression, using double x
	 * @return source for the new class implementing Function interface using the
	 *         expression
	 * @throws IOException
	 */
	private String fillTemplate(String packageName, String className, String expression)
		throws IOException {
		if (template == null)
			template = readTemplate();

		// simplest "template processor":
		String source = template.replace("$packageName", packageName)//
			.replace("$imports", className)//
			.replace("$parameters", className)//
			.replace("$className", className)//
			.replace("$expression", expression);



		return source;
	}

	/**
	 * Read the Function source template
	 *
	 * @return a source template
	 * @throws IOException
	 */
	private String readTemplate() throws IOException {
		InputStream is = CharSequenceCompiler.class.getResourceAsStream("CalculatedField.java.template");
		int size = is.available();
		byte bytes[] = new byte[size];
		if (size != is.read(bytes, 0, size))
			throw new IOException();
		return new String(bytes, "US-ASCII");
	}

	/**
	 * Log diagnostics into the error JTextArea
	 *
	 * @param diagnostics
	 *           iterable compiler diagnostics
	 */
	private void log(final DiagnosticCollector<JavaFileObject> diagnostics) {
		final StringBuilder msgs = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
			.getDiagnostics()) {
			msgs.append(diagnostic.getMessage(null)).append("\n");
		}
		logger.error(msgs.toString());

	}
}

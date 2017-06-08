package am.profclub.ws.calculation.service;

import am.profclub.ws.calculation.converter.*;
import am.profclub.ws.calculation.tool.*;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.tools.*;
import java.io.*;
import java.util.*;

/**
 * Created by admin on 6/2/17.
 */
@RestController
public class JexlToClassService {

	Logger logger = Logger.getLogger(JexlToClassService.class);

	// Create a CharSequenceCompiler instance which is used to compile
	// expressions into Java classes which are then used to create the XY plots.
	// The -target 1.5 options are simply an example of how to pass javac
	// compiler
	// options (the generated source in this example is Java 1.5 compatible.)
	private final CharSequenceCompiler<Function> compiler = new CharSequenceCompiler<Function>(
		getClass().getClassLoader(), Arrays.asList(new String[] { "-target", "1.7" }));
	// for unique class names
	private int classNameSuffix = 0;
	// package name; a random number is appended
	private static final String PACKAGE_NAME = "am.profclub.ws.calculation.runtime";
	// for secure package name
	private static final Random random = new Random();
	// the Java source template
	private String template;

	@Autowired
	private Converter converter;

	@RequestMapping(path = "/toClass")
	public String convert(@RequestParam(name = "domain", required = true, value = "domain") String domain,
						  @RequestParam(name = "expression", required = true, value = "expression") String expression
	)

	{
		return converter.convert(domain, expression);
	}

	@RequestMapping(path = "/calc")
	public String function(@RequestParam(name = "expression", required = true, value = "expression") String expression,
						   @RequestParam(value = "val") double val) {
		Function function = newFunction(expression);
		return String.valueOf(function.f(val));
	}


	/**
	 * Generate Java source for a Function which computes f(x)=expr
	 *
	 * @param expr
	 *           String representation of Java expression that returns a double
	 *           value for an input value x. The class in which this expression
	 *           is embedded uses static import for all the members of the
	 *           java.lang.Math class so they can be accessed without
	 *           qualification.
	 * @return an object which computes the function denoted by expr
	 */
	Function newFunction(final String expr) {
		try {
			// generate semi-secure unique package and class names
			final String packageName = PACKAGE_NAME + digits();
			final String className = "Fx_" + (classNameSuffix++) + digits();
			final String qName = packageName + '.' + className;
			// generate the source class as String
			final String source = fillTemplate(packageName, className, expr);
			// compile the generated Java source
			final DiagnosticCollector<JavaFileObject> errs = new DiagnosticCollector<JavaFileObject>();
			Class<Function> compiledFunction = compiler.compile(qName, source, errs,
				new Class<?>[] { Function.class });
			log(errs);
			return compiledFunction.newInstance();
		} catch (CharSequenceCompilerException e) {
			log(e.getDiagnostics());
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return NULL_FUNCTION;
	}

	/**
	 * @return random hex digits with a '_' prefix
	 */
	private String digits() {
		return '_' + Long.toHexString(random.nextLong());
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
		InputStream is = CharSequenceCompiler.class.getResourceAsStream("Function.java.template");
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

	/**
	 * Null Object pattern to use when there are exceptions with the function
	 * expression.
	 */
	static final Function NULL_FUNCTION = new Function() {
		public double f(final double x) {
			return 0.0;
		}
	};
 }

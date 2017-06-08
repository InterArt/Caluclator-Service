package am.profclub.ws.calculation.transformer;

import am.profclub.ws.calculation.exception.*;
import am.profclub.ws.calculation.util.*;

/**
 * Created by admin on 6/2/17.
 */
public abstract class BaseTransformer implements Transformer {

	@Override
	public String transform(String arg) {
		if (StringHelper.isBlank(arg))
			throw new InvalidArgumentException(String.format("%s is invalid", arg));

		StringBuilder transformBuilder = new StringBuilder("_");
		arg = arg.replaceAll(" ", "");
		transformBuilder.append(arg).append("_");
		return transformBuilder.toString();
	}
}

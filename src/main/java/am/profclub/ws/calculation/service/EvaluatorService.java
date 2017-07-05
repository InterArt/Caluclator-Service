package am.profclub.ws.calculation.service;

import am.profclub.ws.calculation.dto.request.*;

import java.io.*;

/**
 * Created by admin on 7/3/17.
 */
public interface EvaluatorService {

	void check(String domain, String fieldName);

	Object eval(String domain, String fieldName, ParamListDto paramListDto);

	InputStream getGeneratedClass(String domain, String fieldName);

	void toClass(String domain, JexlParamDto jexlParamDto);
}

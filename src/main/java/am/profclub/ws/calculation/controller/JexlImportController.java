package am.profclub.ws.calculation.controller;

import am.profclub.ws.calculation.dto.request.*;
import am.profclub.ws.calculation.dto.response.*;
import am.profclub.ws.calculation.service.*;
import am.profclub.ws.calculation.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;
import java.security.*;

/**
 * Created by admin on 7/3/17.
 */
@RequestMapping("/jexl")
public class JexlImportController extends BaseController {


	@Autowired
	private EvaluatorService evaluatorService;

	@RequestMapping(value = "/{domain}", method = RequestMethod.POST)
	public ResponseDto importJexl(@PathParam("domain") String domain,
								  @RequestBody JexlParamDto jexlParamDto) {

		if (StringHelper.isBlank(domain))
			throw new InvalidParameterException("domain can not be null");

		return null;
	}
}

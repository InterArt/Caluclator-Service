package am.profclub.ws.calculation.controller;

import am.profclub.ws.calculation.dto.request.*;
import am.profclub.ws.calculation.dto.response.*;
import am.profclub.ws.calculation.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;

@RequestMapping("/eval")
@RestController
public class EvaluatorController extends BaseController {


	@Autowired
	private EvaluatorService evaluatorService;

	@RequestMapping(value = "/{domain}/{fieldName}", method = RequestMethod.POST)
	public EvalResponse evaluate(@PathParam("domain") String domain,
								 @PathParam("fieldName") String fieldName,
								 @RequestBody ParamListDto paramListDto) {

		return null;
	}

}

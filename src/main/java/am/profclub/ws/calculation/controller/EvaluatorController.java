package am.profclub.ws.calculation.controller;

import am.profclub.ws.calculation.dto.request.*;
import am.profclub.ws.calculation.dto.response.*;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;

@RequestMapping("/eval")
@RestController
public class EvaluatorController extends BaseController {


	@RequestMapping("/{domain}/{fieldName}")
	public EvalResponse evaluate(@PathParam("domain") String domain,
								 @PathParam("fieldName") String fieldName,
								 @RequestBody ParamListDto paramListDto) {

		return null;
	}

}

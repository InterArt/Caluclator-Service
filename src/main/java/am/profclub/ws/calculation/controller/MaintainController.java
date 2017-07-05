package am.profclub.ws.calculation.controller;

import am.profclub.ws.calculation.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;

@RequestMapping("/class")
@RestController
public class MaintainController extends BaseController {

	@Autowired
	private EvaluatorService evaluatorService;

	@RequestMapping(value = "/{domain}/{paramName}", method = RequestMethod.GET)
	public String check(@PathParam("domain") String domain,
						@PathParam("paramName") String paramName) {

		return null;
	}
}

package am.profclub.ws.calculation.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;

@RequestMapping("/check")
@RestController
public class CheckController extends BaseController {

	@RequestMapping("/{domain}/{paramName}")
	public String check(@PathParam("domain") String domain,
						@PathParam("paramName") String paramName) {

		return null;
	}


}

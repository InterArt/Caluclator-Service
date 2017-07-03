package am.profclub.ws.calculation.controller;

import am.profclub.ws.calculation.dto.request.*;
import am.profclub.ws.calculation.dto.response.*;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.*;

/**
 * Created by admin on 7/3/17.
 */
@RequestMapping("/jexl")
public class JexlImportController extends BaseController {

	@RequestMapping("/{domain}")
	public ResponseDto importJexl(@PathParam("domain") String domain,
								  @RequestBody JexlParamDto jexlParamDto) {

		return null;
	}
}

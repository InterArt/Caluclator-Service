package am.profclub.ws.calculation.dto.request;

import java.io.*;

/**
 * Created by admin on 7/3/17.
 */
public class JexlParamDto implements Serializable {

	private String jexlParam;

	private String[] calcFields;

	public String getJexlParam() {
		return jexlParam;
	}

	public void setJexlParam(String jexlParam) {
		this.jexlParam = jexlParam;
	}

	public String[] getCalcFields() {
		return calcFields;
	}

	public void setCalcFields(String[] calcFields) {
		this.calcFields = calcFields;
	}
}

package am.profclub.ws.calculation.dto.request;

import org.hibernate.validator.constraints.*;

import java.io.*;

/**
 * Created by admin on 7/3/17.
 */
public class JexlParamDto implements Serializable {

	@NotEmpty
	private String jexlParam;

	@NotEmpty
	private String expression;

	private String[] calcFields;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

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

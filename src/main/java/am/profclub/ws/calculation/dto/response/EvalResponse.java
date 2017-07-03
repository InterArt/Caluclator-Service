package am.profclub.ws.calculation.dto.response;

/**
 * Created by admin on 7/3/17.
 */
public class EvalResponse extends ResponseDto {

	private Object value;

	public EvalResponse(String message, Object value) {
		super(message);
		this.value = value;
	}

	public EvalResponse(String message, int code, Object value) {
		super(message, code);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

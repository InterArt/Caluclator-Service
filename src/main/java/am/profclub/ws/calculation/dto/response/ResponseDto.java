package am.profclub.ws.calculation.dto.response;

/**
 * Created by admin on 7/3/17.
 */
public abstract class ResponseDto {

	private String message;

	private int code;

	public ResponseDto(String message) {
		this(message, 200);
	}

	public ResponseDto(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

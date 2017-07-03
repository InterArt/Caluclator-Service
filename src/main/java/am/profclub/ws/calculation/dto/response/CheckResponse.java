package am.profclub.ws.calculation.dto.response;

/**
 * Created by admin on 7/3/17.
 */
public class CheckResponse extends ResponseDto {

	private boolean ready;

	public CheckResponse(String message, boolean ready) {
		super(message);
		this.ready = ready;
	}

	public CheckResponse(String message, int code, boolean ready) {
		super(message, code);
		this.ready = ready;
	}

	public CheckResponse(boolean ready) {
		this(null, ready);
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}

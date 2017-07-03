package am.profclub.ws.calculation.dto.request;

import java.io.*;

/**
 * Created by admin on 7/3/17.
 */
public class ParamDto implements Serializable {

	private String name;

	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

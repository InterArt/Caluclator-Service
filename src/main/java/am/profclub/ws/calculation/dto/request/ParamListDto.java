package am.profclub.ws.calculation.dto.request;

import java.io.*;
import java.util.*;

/**
 * Created by admin on 7/3/17.
 */
public class ParamListDto implements Serializable{

	private String fieldName;

	private List<ParamDto> params;

	private ParamListDto data;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<ParamDto> getParams() {
		return params;
	}

	public void setParams(List<ParamDto> params) {
		this.params = params;
	}

	public ParamListDto getData() {
		return data;
	}

	public void setData(ParamListDto data) {
		this.data = data;
	}

	/*
	{
		data: {
			fieldName : {fieldName},
			params:
			[
				param1: val1,
				param2: val2,
				param3: val3,
			],

			data: {
				fieldName : {fieldName},
				params:
				[
					param1: val1,
					param2: val2,
					param3: val3,
				],
			}

		}

	}
	 */

}

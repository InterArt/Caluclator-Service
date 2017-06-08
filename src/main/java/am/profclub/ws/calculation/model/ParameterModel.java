package am.profclub.ws.calculation.model;

import java.util.*;

/**
 * Created by admin on 6/3/17.
 */
public class ParameterModel {
	String label;
	String description;
	String expression;
	List<ParameterModel> parameterModelList;
	/*
	Example
	{
		domain: mycustomer


		label: Test Parameter,
		description: some string,
		expression: IF (val == '1', CONCAT(param1, param2), CONCAT(project.name, param3))

		val 	- String argument
		param1  - calculated field
		param2  - calculated field
		param3  - text field - String argument
		project.name - String argument
	}

	calculated fields[param1 and param2] should be included as Object in new generated _TestParameter_.java class

	Desired java class will be presented as following

	package am.profclub.calculation.mycustomer;

	public class _TestParameter_ {

		public static String execute(_Param1_ param1, _Param2_ param2, String val, String projectName) {
			StringBuilder builder = new StringBuilder();
			if (val.equal("1")) {
				builder.append(param1.execute()).append(param2.execute());
			} else {
				builder.append(projectName).append(param3);
			}

			return builder.toString();
		}
	}

	 */
}

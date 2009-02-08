package br.miranda.domain;

import java.util.Map;

import br.miranda.annotation.ActionMethod;
import br.miranda.domain.enums.MethodType;


public abstract class ActionBean {
	public static final String MAIN_METHOD = "execute";
	
	private static Map<String, String[]> parameters;
	
	@ActionMethod(type = MethodType.DEFAULT)
	public abstract String execute();


	public static Map<String, String[]> getParameters() {
		return parameters;
	}

	public static void setParameters(Map<String, String[]> parameters) {
		ActionBean.parameters = parameters;
	}
}

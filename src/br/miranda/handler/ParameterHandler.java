package br.miranda.handler;

import java.util.Map;

public class ParameterHandler<T> extends Handler {
	
	private Map<String, String> parameters;
	private T action;
	
	public ParameterHandler(T action, Map<String, String> parameters){
		this.action = action;
		this.parameters = parameters;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public T getAction() {
		return action;
	}

	public void setAction(T action) {
		this.action = action;
	}
	
	
}

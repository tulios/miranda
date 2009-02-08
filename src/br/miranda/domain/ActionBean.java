package br.miranda.domain;

import br.miranda.annotation.ActionMethod;
import br.miranda.domain.enums.MethodType;


public abstract class ActionBean {
	public static final String MAIN_METHOD = "execute";
	
	@ActionMethod(type = MethodType.DEFAULT)
	public abstract String execute();
	
}

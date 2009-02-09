package br.miranda.action;

import br.miranda.annotation.Action;
import br.miranda.annotation.ActionMethod;
import br.miranda.domain.ActionBean;
import br.miranda.domain.Business;
import br.miranda.domain.enums.MethodType;

@Action(url = "business")
public class BusinessAction extends ActionBean{
	
	private Business business;
	
	@Override
	@ActionMethod(type = MethodType.DEFAULT)
	public String execute() {
		System.out.println("-> start to execute method of Business action! =]");
		return "result.jsp";
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
}

package br.miranda.handler;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.Mirror;
import br.miranda.annotation.ActionMethod;
import br.miranda.domain.ActionBean;
import br.miranda.domain.enums.MethodType;
import br.miranda.util.MirandaUtil;


public class ActionHandler extends Handler {
	private String[] actionPackages;
	private String actionName;

	private Object action;
	private MethodType methodType;

	public ActionHandler(String actionName, String[] actionPackages){
		this.actionName = actionName;
		this.actionPackages = actionPackages;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void execute() {

		actionName = MirandaUtil.capitalizar(actionName);
		//search in all packages
		for (String actionPackage: actionPackages){
			//prepare de path and get the Class
			String actionPath = actionPackage+"."+actionName;
			Class actionClass = Mirror.reflectClass(actionPath);

			//is a @Action class?
			List<Annotation> list = Mirror.on(actionClass).reflectAll().annotations().atClass();
			if (list != null && list.size() > 0 && MirandaUtil.hasAnAction(list)){
				//create the object
				action = Mirror.on(actionClass).invoke().constructor().withoutArgs();
			}
		}
	}

	public String executeMethod(String method){
		String returnValue = null;

		if (method != null){

		//main method of the action
		}else{
			ActionMethod actionMethod = Mirror.on(action.getClass()).
			reflect().
			annotation(ActionMethod.class).
			atMethod(ActionBean.MAIN_METHOD).
			withoutArgs();
			
			methodType = actionMethod.type();
			
			if (action instanceof ActionBean){
				ActionBean actionBean = (ActionBean) action;
				returnValue = actionBean.execute();

			}else{
				returnValue = (String) Mirror.on(action).invoke().method(ActionBean.MAIN_METHOD).withoutArgs();
			}

		}
		return returnValue;
	}

	public Object getAction() {
		return action;
	}

	public MethodType getMethodType(){
		return methodType;
	}
}




















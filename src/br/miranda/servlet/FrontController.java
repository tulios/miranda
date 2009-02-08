package br.miranda.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.miranda.handler.ActionHandler;
import br.miranda.handler.ParameterHandler;
import br.miranda.util.MirandaUtil;

@SuppressWarnings("serial")
public class FrontController extends ServletMaster {
	@SuppressWarnings("unchecked")
	private Map<String, Class> actions;
	private String[] actionPackages;
	private String extension;
	
	@Override
	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		actionPackages = this.getServletConfig().getInitParameter("action-packages").split(";");
		extension = "."+this.getServletConfig().getInitParameter("extension");
		actions = new HashMap<String, Class>();
		
		System.out.println("\nMiranda.Framework .: Start\n");
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		String path = MirandaUtil.prepareUrl(url);
		System.out.println("Path: "+path);	   
	    
		//split up url
	    String[] parts = MirandaUtil.splitPath(path);
	    
	    String actionName = null;
	    String methodName = null;
	    
	    //pick up the parts, or not
	    switch(parts.length){
	    	case 1: actionName = parts[0].replace(extension, ""); break;
	    	case 2: actionName = parts[0]; methodName = parts[1].replace(extension, ""); break;
	    }
	    	    
    	ActionHandler actionHandler = new ActionHandler(actionName, actionPackages);
    	actionHandler.execute();
    	
    	Object action = actionHandler.getAction();
    	
    	if (action != null){
    		//store in the actions map
    		actions.put(action.getClass().getSimpleName(), action.getClass());

    		//bind parameters with action
    		ParameterHandler parameterHandler = new ParameterHandler(action, req.getParameterMap());
    		parameterHandler.execute();
    		    		
    		String destination = actionHandler.executeMethod(methodName);
    		
    		switch(actionHandler.getMethodType()){
				case CHAIN:
					//TODO retorno terá "/" separando nome da action do nome do método caso seja em
					//outra action, ex: bussinesAction/doSomething ou somente businessAction/ caso queira
					//executar o método padrão, execute. Se não tiver "/" é por que é um método da mesma
					//action.
					break;
					
				default:
					MirandaUtil.dispatch(destination, req, resp);
			}
    		
    	}else{
    		//TODO throws Exception
    		System.err.println("Miranda.Framework .: Invalid action!");
    	}
    	
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doPost(req, resp);
	}
	
}




























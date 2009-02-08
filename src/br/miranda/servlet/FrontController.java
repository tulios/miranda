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
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		actionPackages = this.getServletConfig().getInitParameter("action-packages").split(";");
		actions = new HashMap<String, Class>();
		
		System.out.println("\nMiranda.Framework .: Start\n");
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String path = req.getPathInfo();
		System.out.println("Path: "+path);	   
	    
	    String[] parts = MirandaUtil.splitUrl(path);
	    
	    String actionName = null;
	    String methodName = null;
	    
	    switch(parts.length){
	    	case 1: actionName = parts[0]; break;
	    	case 2: actionName = parts[0]; methodName = parts[1]; break;
	    }
	    
    	ActionHandler actionHandler = new ActionHandler(actionName, actionPackages);
    	actionHandler.execute();
    	
    	Object action = actionHandler.getAction();
    	if (action != null){
    		//store in the actions map
    		actions.put(action.getClass().getSimpleName(), action.getClass());
    		
    		ParameterHandler parameterHandler = new ParameterHandler(action, req.getParameterMap());
    		parameterHandler.execute();
    		
    		actionHandler.executeMethod(methodName);
    		
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




























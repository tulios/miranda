package br.miranda.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.miranda.annotation.Action;

public class MirandaUtil {
	
	public static String capitalizar(String s){		
		return s.replaceFirst(String.valueOf(s.charAt(0)), (String.valueOf(s.charAt(0))).toUpperCase());
	}
	
	public static String prepareUrl(String url){
		String[] parts = url.split("/");
		if (parts != null && parts.length > 0){
			return parts[parts.length - 1];
		}
		return null;
	}
	
	public static String[] splitPath(String url){
		String[] parts = url.split("!");

		if (parts != null && parts.length > 0){
			String[] aux = parts.clone();
			List<String> list = new ArrayList<String>();
			for (String s: aux){
				if (s.trim().length() > 0){
					list.add(s);
				}
			}
			parts = new String[list.size()];
			return list.toArray(parts);
		}
		return null;
	}
	
	public static boolean hasAnAction(List<Annotation> list){
		for (Annotation a : list){	
			if(a.annotationType().equals(Action.class)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Object getPrimitiveValues(Class clazz, String value){
		
		if (clazz.getName().equalsIgnoreCase("int")){
			return new Integer(value);
		}else if (clazz.getName().equalsIgnoreCase("long")){
			return new Long(value);
		}else if (clazz.getName().equalsIgnoreCase("float")){
			return new Float(value);
		}else if (clazz.getName().equalsIgnoreCase("double")){
			return new Double(value);
		}else if (clazz.getName().equalsIgnoreCase("short")){
			return new Short(value);
		}else if (clazz.getName().equalsIgnoreCase("byte")){
			return new Byte(value);
		}else if (clazz.getName().equalsIgnoreCase("boolean")){
			return new Boolean(value);
		}else if (clazz.getName().equalsIgnoreCase("char")){
			return new Character(value.charAt(0));
		}
			
		return value;
	}
	
	public static void dispatch(String destination, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher(destination);
		rd.forward(req, resp);
	}
	
	public static void redirect(String destination, HttpServletResponse resp) throws IOException{
		resp.sendRedirect(destination);
	}
}
































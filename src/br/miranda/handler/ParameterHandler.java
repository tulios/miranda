package br.miranda.handler;

import java.lang.reflect.Field;
import java.util.Map;

import net.vidageek.mirror.Mirror;
import br.miranda.domain.ActionBean;
import br.miranda.util.MirandaUtil;

public class ParameterHandler extends Handler {

	private Map<String, String[]> parameters;
	private Object action;

	public ParameterHandler(Object action, Map<String, String[]> parameters){
		this.action = action;
		this.parameters = parameters;
	}

	@Override
	public void execute() {

		for (String p: parameters.keySet()){
			fillAttributes(action, p, p);
		}
		
		ActionBean.setParameters(parameters);
	}
	
	private void fillAttributes(Object ref, String attribute, String originalAttribute){
		//composed attribute
		if (attribute.contains(".")){
			//split the composed attribute
			String[] parts = attribute.split("\\.");
			//get the value
			Object obj = Mirror.on(ref).invoke().method("get"+MirandaUtil.capitalizar(parts[0])).withoutArgs();
			//if the object hasn't a value
			if (obj == null){
				//pick up the field type
				Field field = Mirror.on(ref.getClass()).reflect().field(parts[0]);
				//create
				Object value = Mirror.on(field.getType()).invoke().constructor().withoutArgs();
				//set
				Mirror.on(ref).invoke().method("set"+MirandaUtil.capitalizar(parts[0])).withArgs(value);
				obj = value;
			}
			//continue to fill
			fillAttributes(obj, remount(parts), originalAttribute);
			
		//atomic attibute
		}else{
			//get the value of request
			String[] value = parameters.get(originalAttribute);
			//get the field of the value
			Field field = Mirror.on(ref.getClass()).reflect().field(attribute);
			//obtain the real value, convert if necessary
			Object methodValue = MirandaUtil.getPrimitiveValues(field.getType(), value[0]);
			//invoke the setter method
			Mirror.on(ref).invoke().method("set"+MirandaUtil.capitalizar(attribute)).withArgs(methodValue);			
		}
	}
	
	/**
	 * Remount the attribute name, exemple:
	 * 
	 * 		[business, business, value] => business.business.value
	 * 
	 * @param parts - {@link String[]}
	 * @return {@link String}
	 */
	private String remount(String[] parts){
		if (parts.length > 1){
			StringBuilder sb = new StringBuilder();
			for (int x=1; x<parts.length; x++){
				if (x == parts.length - 1){
					sb.append(parts[x]);
				}else{
					sb.append(parts[x]+".");
				}
			}
			return sb.toString();
		}else{
			return parts[0];
		}
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

}

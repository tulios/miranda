package br.miranda.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class Value extends TagSupport{
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		Object obj = this.pageContext.getRequest().getAttribute(getName());
		try {
			this.pageContext.getOut().print(obj);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}

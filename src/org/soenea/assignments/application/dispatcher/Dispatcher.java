package org.soenea.assignments.application.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Dispatcher extends org.dsrg.soenea.application.servlet.dispatcher.Dispatcher 
{
	protected Long getParamAndSetAsLongAttr(String pName) throws ServletException {
		try {
			String paramAsString = this.myRequest.getParameter(pName);
			if (paramAsString == null) {
				paramAsString = (String) this.myHelper.getAttribute(pName);
			}
			long param = Long.parseLong(paramAsString);
			this.myHelper.setRequestAttribute(pName, param);
			return param;
		} catch (NumberFormatException e) {
			throw new ServletException("invalid " + pName, e);
		}
	}

	/**
	 * Redirects to: <context-path><servlet-path>/p
	 * @param p the last part of the path to be redirected to.
	 * @throws IOException
	 */
	public void relativeRedirect(String p) throws IOException {
	  String path = this.myRequest.getContextPath() + this.myRequest.getServletPath() + "/" + p;
	  this.myResponse.sendRedirect(path);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
	}

}

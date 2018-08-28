package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;


import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.PersonProxy;


public class ViewPersonCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String userName = request.getParameter("userName");
			IPerson person = new PersonProxy(userName);
			request.setAttribute("person", person);

			return "/WEB-INF/jsp/ViewPersonTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
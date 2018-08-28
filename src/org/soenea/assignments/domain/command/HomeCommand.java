package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand {

	public String execute(HttpServletRequest request) throws Exception {
		try {
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

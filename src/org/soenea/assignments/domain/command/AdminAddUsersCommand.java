package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;

public class AdminAddUsersCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String user = request.getParameter("user");
			request.setAttribute("user",user );
			return "/WEB-INF/jsp/AddUsersTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

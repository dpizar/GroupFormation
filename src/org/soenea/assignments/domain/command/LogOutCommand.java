package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand {

	public String execute(HttpServletRequest request) throws Exception {
		try {			
			HttpSession session = request.getSession(true);
			session.invalidate();
			return "/WEB-INF/jsp/logout.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

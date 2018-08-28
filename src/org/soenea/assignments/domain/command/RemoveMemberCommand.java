package org.soenea.assignments.domain.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.PersonProxy;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class RemoveMemberCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String userName = request.getParameter("userName");
			IPerson person = new PersonProxy(userName);
			request.setAttribute("person", person);

			return "/WEB-INF/jsp/ViewRemovePersonTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
package org.soenea.assignments.domain.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class BrowsePeopleCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			Person user = (Person) session.getAttribute("userName");
			request.setAttribute("people", PersonMapper.findAllNoGroupPeople());
			request.setAttribute("Members",PersonMapper.findGrpUser2(user.getUserName(),user.getGroupId()));
			return "/WEB-INF/jsp/BrowsePeopleTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

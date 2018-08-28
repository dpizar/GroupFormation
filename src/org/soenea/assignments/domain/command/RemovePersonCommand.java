package org.soenea.assignments.domain.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.PersonProxy;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class RemovePersonCommand {
	public String execute(HttpServletRequest request) throws Exception {
		String userName = request.getParameter("PersonName");
		int userVersion = Integer.parseInt(request.getParameter("PersonVersion"));
		IPerson personp = new PersonProxy(userName);
		HttpSession session = request.getSession(true);
		Person user = (Person) session.getAttribute("userName");
		
		if(userVersion!=personp.getVersion())
		{
			String warning5 = "user was already Removed";
			request.setAttribute("warning4", warning5);
			return "BrowsePeople";
		}
		else
		{
			Person p = PersonMapper.findUser(userName);
			p.setGroupId(0);
			p.setVersion(p.getVersion()+1);
			PersonMapper.update(p);
			return "BrowsePeople";
		}

				
	}

}
package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.soenea.assignments.domain.model.group.group;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.PersonProxy;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class DeleteGroupCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {	    
			HttpSession session = request.getSession(true);
			Person user = (Person) session.getAttribute("userName");
			group currentgrp = (group) session.getAttribute("Group");
			IPerson person = new PersonProxy(user.getUserName());
			if(user.getVersion()!=person.getVersion())
			{
				if(person.getGroupId()!=0)
				{
					user = PersonMapper.findUser(user.getUserName());
					session.setAttribute("userName",user);
					currentgrp = GroupMapper.findGroup(user.getGroupId());
					session.setAttribute("Group", currentgrp);
					String warning3 ="Your are in a new group";
					request.setAttribute("warning3", warning3);
					return "BrowsePeople";
				}
				else
				{
					user = PersonMapper.findUser(user.getUserName());
					session.setAttribute("userName",user);
					String warning3 ="you already deleted your group";
					request.setAttribute("warning3", warning3);
					return "BrowseGroup";
				}
			}
			session.removeAttribute("Group");
			if(person.getGroupId()!=0)
			{
				//remove all other members
				PersonMapper.RemovePersonGroup(user.getVersion()+1,user.getGroupId());
				//remove current member
				user.setGroupId(0);
				user.setVersion(user.getVersion()+1);
				GroupMapper.delete(currentgrp);
				PersonMapper.update(user);
				session.setAttribute("userName", user);
				
				return "BrowseGroup";
			}
			else
			{
				String warning3 ="Your group has being disband. You are no longer in a group";
				request.setAttribute("warning3", warning3);
			}
			return "BrowseGroup";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}

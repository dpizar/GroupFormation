package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.soenea.assignments.domain.model.group.GroupProxy;
import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.group;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.PersonProxy;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class EditGroupCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {	    
			HttpSession session = request.getSession(true);
			Person user = (Person) session.getAttribute("userName");
			IPerson person = new PersonProxy(user.getUserName());
			if(user.getVersion()!=person.getVersion())
			{
				
				if(person.getGroupId()==0)
				{
					session.removeAttribute("Group");
					user = PersonMapper.findUser(user.getUserName());
					session.setAttribute("userName", user);
					String warning3 ="Your group has being disband. You are no longer in a group";
					request.setAttribute("warning3", warning3);
					return "BrowseGroup";
				}
				if(person.getGroupId()!=user.getGroupId())
				{
					user = PersonMapper.findUser(user.getUserName());
					session.setAttribute("userName", user);
					group newgroup = GroupMapper.findGroup(user.getGroupId());
					session.setAttribute("Group", newgroup);
					String warning3 ="Your group has being changed already";
					request.setAttribute("warning3", warning3);
					return "BrowsePeople";
				}
			}
			group currentgrp = (group) session.getAttribute("Group");
			String newGrpName = request.getParameter("NewGroupName");
			IGroup group = new GroupProxy(user.getGroupId());
			group newgroupName = GroupMapper.findGroup(newGrpName);
			if(newgroupName!=null)
			{
				String warning4 = "Group Name has being taken";
				request.setAttribute("warning4", warning4);
				return "BrowsePeople";
			}
			if(group.getversion()==currentgrp.getversion())
			{
				currentgrp.setgroupName(newGrpName);
				currentgrp.setversion(currentgrp.getversion()+1);
				GroupMapper.update(currentgrp);
				session.setAttribute("Group", currentgrp);
				return "BrowsePeople";
			}
			else
			{
				String warning2 = "cannot modify the new group name! either the name already in DB or another member in your group has updated the group name";
				request.setAttribute("warning2", warning2);
				currentgrp = GroupMapper.findGroup(currentgrp.getgroupId1());
				session.setAttribute("Group", currentgrp);
				return "BrowsePeople";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

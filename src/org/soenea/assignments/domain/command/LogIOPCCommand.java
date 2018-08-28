package org.soenea.assignments.domain.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soenea.assignments.domain.model.group.group;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class LogIOPCCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("logCoomand");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		Person user = PersonMapper.authenticate(userName, password);
		HttpSession session = request.getSession(true);

		if (user != null) {
			//request.getSession().setAttribute("user", user);
			session.setAttribute("userName", user);
			if(PersonMapper.findUser(user.getUserName()).getUserType()==0){
				return "assignments/AdminAddUsers";
			}
			else if(PersonMapper.getGroupId(user)==0){
				return "assignments/BrowseGroup";
			}
			else{	      
				group usrgrp = GroupMapper.findGroup(user.getGroupId());
				session.setAttribute("Group", usrgrp);
				return "assignments/BrowsePeople";
			}
		} else {				
			//System.out.println("aaa");
			//System.out.println(password);
			request.setAttribute("error", "Unknown user, please try again!!");			
			return "/index.jsp";
		}


	}
}

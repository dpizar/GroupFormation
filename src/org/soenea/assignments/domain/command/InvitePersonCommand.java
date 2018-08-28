package org.soenea.assignments.domain.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.group;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.invite.Invite;
import org.soenea.assignments.domain.model.invite.mapper.InviteMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;


public class InvitePersonCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String userName = request.getParameter("userName");//Get the user name you wish to send an invitation to.
			//Get group id and name from the sender.
			HttpSession session = request.getSession(true);
			group group = (group) session.getAttribute("Group");			
			int groupSenderId=group.getgroupId1();	
			String groupName=group.getgroupName();
			//Insert new Invitation.The first two fields don't matter they will not be used for insertion.
			Invite invite=new Invite(2,123,userName,groupSenderId);
			int success=InviteMapper.insert(invite);
			//set the attributes that will be sent to the template view.
			request.setAttribute("userNameReceiver", userName);
			request.setAttribute("groupName", groupName);
			
			return "/WEB-INF/jsp/ViewInvite.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}										
	}
}

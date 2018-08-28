package org.soenea.assignments.domain.command;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soenea.assignments.domain.model.invite.Invite;
import org.soenea.assignments.domain.model.invite.mapper.InviteMapper;
import org.soenea.assignments.domain.model.person.Person;


public class BrowseInviteCommand {
	public String execute(HttpServletRequest request) throws Exception {
					
			try
			{			
				HttpSession session = request.getSession(true);
				Person user = (Person) session.getAttribute("userName");
				
				List<Invite> fullList = InviteMapper.findAll();			
								
				String userid = user.getUserName();//Get users name
				System.out.println("UserNAME:"+userid);
				//Filter invites to the ones that belong to the current user.
				List<Invite> iList = new ArrayList<Invite>();
				for(Invite inv: fullList)
				{
					if(inv.getUserId().equalsIgnoreCase(userid) )
							iList.add(inv);
					System.out.println("List Name:"+inv.getUserId());
				}

				request.setAttribute("Invites", iList);
					
				DbRegistry.closeDbConnectionIfNeeded();
				return "/WEB-INF/jsp/BrowseInvites.jsp";
					
			}
			catch (SQLException e) 
			{

				String s = e.getMessage();
				String s2 = "\nSQL error comes from the BrowseInvites controller";
				request.setAttribute("ERROR", s+s2);
				return null;
			} 
			
		
	}

}
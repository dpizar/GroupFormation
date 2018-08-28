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

public class CreateGroupCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			Person user = (Person) session.getAttribute("userName");
			
			String groupName = request.getParameter("groupname");
			IPerson personp  = new PersonProxy(user.getUserName());
			group group = GroupMapper.findGroup(groupName);
			if(group!=null)
			{
				String warning4 = "Group Name has being taken";
				request.setAttribute("warning4", warning4);
				return "BrowseGroup";
			}
			if(personp.getVersion()==user.getVersion())
			{
				GroupMapper.insertGroup(1, groupName);
				IGroup groupp= new GroupProxy(GroupMapper.findGroup(groupName).getgroupId1());
				user.setGroupId(groupp.getgroupId1());
				user.setVersion(user.getVersion()+1);
				PersonMapper.update(user);
				session.setAttribute("userName",user);
				group = GroupMapper.findGroup(groupp.getgroupId1());
				session.setAttribute("Group", group);
				return "BrowsePeople";
			}
			else
			{
				String warning = "you are already a user in a group!";
				group = GroupMapper.findGroup(personp.getGroupId());
				user = PersonMapper.findUser(user.getUserName());
				request.setAttribute("warning", warning);
				session.setAttribute("userName", user);
				session.setAttribute("Group", group);
				return "BrowsePeople";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

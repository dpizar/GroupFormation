package org.soenea.assignments.domain.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.mapper.GroupMapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class ViewGroupCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {
			String groupName = request.getParameter("groupName");
			IGroup group = GroupMapper.findGroup(groupName);
			ArrayList<IPerson> groupuser = PersonMapper.findGrpUser(group.getgroupId1());
			request.setAttribute("GrpUser", groupuser);
			request.setAttribute("Grp", groupName);
			return "/WEB-INF/jsp/ViewGroupTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
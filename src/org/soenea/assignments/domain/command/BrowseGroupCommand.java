package org.soenea.assignments.domain.command;

import javax.servlet.http.HttpServletRequest;

import org.soenea.assignments.domain.model.group.mapper.GroupMapper;

public class BrowseGroupCommand {
	public String execute(HttpServletRequest request) throws Exception {
		try {	    
			request.setAttribute("Groups", GroupMapper.findAll());
			return "/WEB-INF/jsp/BrowseGroupTV.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

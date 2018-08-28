package org.soenea.assignments.application.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soenea.assignments.domain.command.ViewGroupCommand;


public class ViewGroup extends Dispatcher {
	
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//System.out.println("Browse Group Dispatcher!");
    	ViewGroupCommand lc = new ViewGroupCommand();
		String fw=lc.execute(request);
		System.out.println(fw);
		forward(fw);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
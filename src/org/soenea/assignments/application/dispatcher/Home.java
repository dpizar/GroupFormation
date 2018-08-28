package org.soenea.assignments.application.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soenea.assignments.domain.command.HomeCommand;


public class Home extends Dispatcher {
	
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HomeCommand lc = new HomeCommand();
		String fw=lc.execute(request);
		System.out.println(fw);
		forward(fw);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}

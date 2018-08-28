package org.soenea.assignments.application.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soenea.assignments.domain.command.LogIOPCCommand;


public class LogIOPC extends Dispatcher {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("log dispatcher");
		
		LogIOPCCommand lc = new LogIOPCCommand();
		String fw=lc.execute(request, response);
		System.out.println(fw);
		forward("/"+fw);
	}

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

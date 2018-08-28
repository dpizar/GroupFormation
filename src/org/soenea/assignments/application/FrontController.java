package org.soenea.assignments.application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;
import org.soenea.assignments.application.dispatcher.Dispatcher;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		prepareDbRegistry("");
	}

	public static void prepareDbRegistry(String db_id) {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id+"mySqlTablePrefix");
		} catch (Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dispatcher fc = getFrontCommand(request);
		System.out.println(fc);
		if (fc == null) {
			String msg = "Invalid or unspecified command";
			if (request.getParameter("command") != null)
				msg += " (" + request.getParameter("command") + ")";
			response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
		} else {
			fc.init(request, response);
			fc.execute(request, response);
		}		
	}

	private Dispatcher getFrontCommand(HttpServletRequest request) {
		try {
			String command = getCommandFromPathInfoAndMaybeSetSomeAttributes(request);
			//System.out.println(command);
			if (command == null)
				command = request.getParameter("command");
			if (command == null || command.isEmpty()) {
				return null;
			}
			String fqCommand = "org.soenea.assignments.application.dispatcher."
					+ command;
			return (Dispatcher) Class.forName(fqCommand).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getCommandFromPathInfoAndMaybeSetSomeAttributes(
			HttpServletRequest request) {
		String path = request.getPathInfo();
		System.out.println(path);
		if (path == null)
			return null;

		String command = null;
		if ("/login".equals(path)) {
			command = "LogIOPC";
		}		
		else if("/BrowseGroup".equals(path)){
			command= "BrowseGroup";
		}		
		else if("/BrowsePeople".equals(path)){
			command= "BrowsePeople";
		}
		else if("/AdminAddUsers".equals(path)){
			command= "AdminAddUsers";
		}
		else if("/logout".equals(path)){
			command= "LogOut";
		}
		else if("/CreateGroup".equals(path)){
			command= "CreateGroup";
		}
		else if("/ViewGroup".equals(path)){
			command= "ViewGroup";
		}
		else if("/EditGroup".equals(path)){
			command= "EditGroup";
		}
		else if("/DeleteGroup".equals(path)){
			command= "DeleteGroup";
		}
		else if("/ViewPerson".equals(path)){
			command= "ViewPerson";
		}
		else if("/RemoveMember".equals(path)){
			command= "RemoveMember";
		}
		else if("/RemovePerson".equals(path)){
			command= "RemovePerson";
		}	
		else if("/BrowseInvites".equals(path)){
			command= "BrowseInvite";
		}
		else if("/ViewInvite".equals(path)){
			command= "InvitePerson";
		}
		/*
		else if("/index.jsp".equals(path)){
			command= "Home";
		}
		*/
		/*
		else {
			Pattern pattern = Pattern.compile("^/(view|inc)/(\\d+)$");
			Matcher matcher = pattern.matcher(path);
			if (matcher.find()) {
				String id = null;
				command = "view".equals(matcher.group(1)) ? "ViewPerson"
						: "IncreaseAge";
				id = matcher.group(2);
				request.setAttribute("id", id);
			}
		}
		*/
		System.out.println("command = " + command);
		return command;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("aaaaaaaaaaaaaaaa");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preProcessRequest(request, response);
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			postProcessRequest(request, response);
		}
	}

	protected void preProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void postProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DbRegistry.closeDbConnectionIfNeeded();
		} catch (Exception e) {}
		ThreadLocalTracker.purgeThreadLocal();
	}
	
}

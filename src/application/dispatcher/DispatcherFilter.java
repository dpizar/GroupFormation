package application.dispatcher;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class DispatcherFilter implements Filter {

	@Override
	public void destroy() {
		// Nothing to do.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String command = getCommandFromPathInfoAndMaybeSetSomeAttributes((HttpServletRequest)request);
		request.setAttribute("command",command);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Nothing to do.
	}


	/**
	 * Attempt to extract the command from the request's pathInfo. Note that
	 * this method might set request attributes.
	 * 
	 * @param request
	 * @return null or the command as a simple class name (i.e. not fully
	 *         qualified with the package).
	 */
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
	
	

}
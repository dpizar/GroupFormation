/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.soenea.assignments.services;

import java.sql.SQLException;

/**
 * The purpose of this is to ensure that database connections are clossed/
 * dropped/nuked when the connection is no longer valid. I've never been 
 * sure that it really works, except that it seemed to have stopped the 
 * problem, and I'm afraid to go back and deliberately break it to see 
 * what is really going on.
 * 
 * 
 * @author Stuart Thiel
 */
public class EAConnectionWrapper {
	/**
	 * @param wrappedConnection
	 */
	public EAConnectionWrapper(SoenEAConnection wrappedConnection) {
		super();
		WrappedConnection = wrappedConnection;
	}
	private SoenEAConnection WrappedConnection;
	
	
	
	public void finalize() throws Throwable{	
		try {
			WrappedConnection.close();
		} catch (SQLException e) {
			//This should be logged somewhere...
		}
		WrappedConnection = null;
		super.finalize();
	}
	
	/**
	 * @return Returns the wrappedConnection.
	 */
	public SoenEAConnection getWrappedConnection() {
		return WrappedConnection;
	}
	/**
	 * @param wrappedConnection The wrappedConnection to set.
	 */
	public void setWrappedConnection(SoenEAConnection wrappedConnection) {
		WrappedConnection = wrappedConnection;
	}
}

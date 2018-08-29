package org.soenea.assignments.services;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An abstract class which should probably become an interface as it has no 
 * implemented methods, just a defined behaviour.
 * 
 * @author Stuart Thiel
 */
public abstract class ConnectionFactory {

	/**
	 * This should be overridden in subclasses.
	 */
	public abstract Connection getConnection() throws SQLException;
	
	public abstract void defaultInitialization() throws SQLException;

}

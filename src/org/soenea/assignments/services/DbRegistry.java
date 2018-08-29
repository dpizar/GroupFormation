package org.soenea.assignments.services;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.soenea.assignments.services.ConnectionFactory;
import org.soenea.assignments.services.EAConnectionWrapper;
import org.soenea.assignments.services.SoenEAConnection;

/**
 * This class provides several public static methods that allow users to quickly and <br/>
 * easily get a hold of database connections. <br/>
 * 
 * While not the cleanest, it has been a mainstay of the soenEA framework and several <br/>
 * other applications since its more thread-safe (yay thread-local) implementation a year <br/>
 * or so ago. <br/><br/>
 * 
 * One of the reasons it is so unclean is the Exception Wrapping mechanism. One has to be <br/>
 * careful with it, and I don't like it at all. <br/>
 * 
 * Another either unclean or questionable issue is the closeDbConnectionIfNeeded() method. <br/>
 * This static method should be called every time, so I end up often putting it in the <br/>
 * finally of the controller's processRequest (or whatever) method. This does ensure that <br/>
 * the database connection is always disposed of properly (as I don't trust the garbage <br/>
 * collection and my EAConnectionWrapper). <br/><br/>
 * 
 * In addition, DbRegistry has been altered to be able to support multiple connections <br/>
 * for each thread of operation. This allows single threads to connect to different databases <br/>
 * in addition to allowing threads to make multiple connections to a database with different <br/>
 * connection types ... such as having both a standard connection as well as one for locking tables.<br/><br/>
 * 
 * While implemented, there were two design possibilities for the how a connection would be created <br/>
 * that were being considered: the first having the caller call:<br/> 
 * DbRegistry.get("someConnectionName").getConnection()   <br/><br/> OR <br/><br/>
 * DbRegistry.getDbConnection("someConnectionName");	<br/><br/>
 * 
 * Both are reasonable suggestions, and only upon closer inspection was the latter chosen and implemented<br/>
 * The first had only a single con : the implementing class would be associated with dbRegistry instead of <br/>
 * only dependent on it. Also, it would be up to the implementing class to know that the static DbRegistry <br/>
 * stores instances of itself for each connection.. and that a non-static one must be retrieved in order to get <br/>
 * the connection (which is non-transparent behavior). The latter choice had no cons and had the pro of making <br/>
 * the aforementioned behavior of DbRegistry completely transparent... which led to the latter being chosen.
 * 
 * @author Stuart Thiel
 */
public class DbRegistry extends ThreadLocal<EAConnectionWrapper> {
	private SQLException InternalException;
	private static HashMap<String, DbRegistry> internalDbRegistry = new HashMap<String, DbRegistry>();
	private ConnectionFactory conFactory;
	private String TablePrefix = null;
	
	private DbRegistry() {
		super();
		ThreadLocalTracker.registerThreadLocal(this);
	}
	
	/**
	 * Returns the appropriate internalDBRegistry based on the passed key
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @return A thread safe DbRegistry 
	 */
	protected static DbRegistry getInternalDBRegistry(String connectionTypeKey) {
		if(internalDbRegistry.get(connectionTypeKey) == null) 
			internalDbRegistry.put(connectionTypeKey, new DbRegistry());
		return internalDbRegistry.get(connectionTypeKey);
	}

	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 * 
	 * @return A DbRegistry
	 */
	protected static DbRegistry getInternalDBRegistry() {
		return getInternalDBRegistry("");
	}
	
	/**
	 * This overrides the initalValue() of ThreadLocal which we extended.
	 */
	protected EAConnectionWrapper initialValue() {
		SoenEAConnection con = null;
		try {
			con = (SoenEAConnection)conFactory.getConnection();
	    } catch (SQLException e) {
	    	setInternalException(e);
	    }
	    return new EAConnectionWrapper(con);
	}
	
	/**
	 * Gets a database connection from a connection type key
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @return a connection of the requested type
	 * @throws SQLException
	 */
	public static SoenEAConnection getDbConnection(String connectionTypeKey) throws SQLException {
		SoenEAConnection con = null;
		EAConnectionWrapper wrappedConnection = null;

		wrappedConnection = (EAConnectionWrapper)getInternalDBRegistry(connectionTypeKey).get();
		if(wrappedConnection == null) {
			wrappedConnection = (EAConnectionWrapper)getInternalDBRegistry(connectionTypeKey).initialValue(); //In case it got closed once already.
			getInternalDBRegistry(connectionTypeKey).set(wrappedConnection);
		}
		con = wrappedConnection.getWrappedConnection();

		if(getInternalDBRegistry(connectionTypeKey).getInternalException() != null) 
			throw getInternalDBRegistry(connectionTypeKey).getInternalException();
	    return con;
	}	
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 */
	public static SoenEAConnection getDbConnection() throws SQLException {
		return getDbConnection("");
	}
	
	/**
	 * This will ensure that the ThreadLocal piece stored for this thread is null.
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @throws SQLException
	 */
	public static void closeDbConnection(String connectionTypeKey) throws SQLException{
		closeDbConnectionIfNeeded(connectionTypeKey);
		if(getInternalDBRegistry(connectionTypeKey).getInternalException() != null) 
			throw getInternalDBRegistry(connectionTypeKey).getInternalException();
	}	
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 */
	public static void closeDbConnection() throws SQLException{
		closeDbConnection("");
	}	
	
	/**
	 * This will ensure that the ThreadLocal piece stored for this thread is null.
	 * 
	 * @throws SQLException
	 */
	public static void closeDbConnectionIfNeeded(String connectionTypeKey) throws SQLException{
		EAConnectionWrapper eaConnectionWrapper = (EAConnectionWrapper)getInternalDBRegistry(connectionTypeKey).get();
		if (eaConnectionWrapper != null) {
			Connection con = (Connection)eaConnectionWrapper.getWrappedConnection();
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { 
					getInternalDBRegistry(connectionTypeKey).setInternalException(e);
				}
			}
		}
		getInternalDBRegistry(connectionTypeKey).set(null);
	}	
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 */
	public static void closeDbConnectionIfNeeded() throws SQLException{
		closeDbConnectionIfNeeded("");
	}	
	
	/**
	 * This method is used to confirm whether or not a connection exists
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @return A boolean. True if the connection isn't null and it's open.
	 * @throws SQLException
	 */
	public static boolean isConnected(String connectionTypeKey) throws SQLException {
	    return getInternalDBRegistry(connectionTypeKey).get() != null && 
	    			!((Connection)((EAConnectionWrapper)getInternalDBRegistry(connectionTypeKey).get()).getWrappedConnection()).isClosed();
	}
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 * @throws SQLException
	 */
	public static boolean isConnected() throws SQLException {
	    return isConnected("");
	}

	//************************ Getters And Setters *************************************
	
	/**
	 * Sets the connection factory for a specific connection type key
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @param con_factory The connection factory to set
	 */
	public static void setConFactory(String connectionTypeKey, ConnectionFactory con_factory) {
		getInternalDBRegistry(connectionTypeKey).conFactory = con_factory;
	}
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 * 
	 * @param con_factory The connection factory to set 
	 */
	public static void setConFactory(ConnectionFactory con_factory) {
		setConFactory("",con_factory);
	}
	
	/**
	 * @return Returns the internalException.
	 */
	protected SQLException getInternalException() {
		return InternalException;
	}
	
	/**
	 * @param internalException The internalException to set.
	 */
	protected void setInternalException(SQLException internalException) {
		InternalException = internalException;
	}
	
	/**
	 * Gets the table prefix 
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @return The table prefix for the DbRegistry
	 */
	public static String getTablePrefix(String connectionTypeKey) {
		if( getInternalDBRegistry(connectionTypeKey).TablePrefix == null) 
			throw new NullPointerException("Undefined Table Prefix");
		return getInternalDBRegistry(connectionTypeKey).TablePrefix;
	}
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 * 
	 * @throws SQLException
	 */
	public static String getTablePrefix() {
		return getTablePrefix("");
	}

	/**
	 * Sets the table prefix 
	 * 
	 * @param connectionTypeKey The type of connection that is desired
	 * @param tablePrefix The string to prefix the tables with
	 */
	public static void setTablePrefix(String connectionTypeKey, String tablePrefix) {
		getInternalDBRegistry(connectionTypeKey).TablePrefix = tablePrefix;
	}
	
	/**
	 * Supports backwards compatibility for SoenEA2. Uses a default connection name
	 * 
	 * @throws SQLException
	 */
	public static void setTablePrefix(String tablePrefix) {
		setTablePrefix("", tablePrefix);
	}
}

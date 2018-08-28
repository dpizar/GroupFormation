package org.soenea.assignments.services.invite.TDG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;



public class InviteTDG {
	
	public static final String BASE_NAME = "invite";
	public static final String TABLE = BASE_NAME;
	public static final String UPDATE = "UPDATE " + TABLE + " AS i set i.version=i.version+1, i.groupid=?, i.userid=? WHERE i.groupid=? AND i.userid=? AND i.version=?;";
	public static final String DELETE = "DELETE FROM " + TABLE + " WHERE version=? AND userid=? AND groupid=?;";
	public static final String INSERT = "INSERT INTO " + TABLE + " (version,userid,groupid) VALUES (?,?,?);";
	
	public static final String SELECT = "SELECT  i.inviteId, i.version, i.userid, i.groupid FROM " + InviteTDG.TABLE + " AS i WHERE i.inviteId;";
	public static final String SELECT_WITH_USER_GROUP = "SELECT  i.inviteId, i.version, i.userid, i.groupid FROM " + InviteTDG.TABLE + " AS i WHERE i.userid=? AND i.groupid=?;";
	public static final String SELECT_ALL = "SELECT i.inviteId, i.version, i.userid, i.groupid FROM " + InviteTDG.TABLE + " AS i;";


	
	
	public static int insert(String userid, long groupid) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		
		ps.setInt(1, 1);//version always inserted @ 1
		ps.setString(2, userid);
		ps.setLong(3, groupid);	
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}

	public static int delete(long version, String userid, long groupid) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		
		ps.setLong(1, version);
		ps.setString(2, userid);
		ps.setLong(3, groupid);
		int count = ps.executeUpdate();
		ps.close();
		
		return count;
	}
	
	public static ResultSet find(long id) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet find(String userid, long groupid) throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_WITH_USER_GROUP);
		
		ps.setString(1, userid);
		ps.setLong(2, groupid);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet findAll() throws SQLException
	{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
	
		return rs;	
	}

}

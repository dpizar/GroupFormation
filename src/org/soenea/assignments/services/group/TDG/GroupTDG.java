package org.soenea.assignments.services.group.TDG;

import org.soenea.assignments.services.DbRegistry;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupTDG {
	public static final String SELECT_ALL = "SELECT * FROM groupp;";
	public static final String INSERT = "INSERT INTO groupp (version,groupName) VALUES (?,?);";
	public static final String SELECTID = "SELECT * FROM groupp WHERE groupId=?;";
	public static final String SELECTNAME = "SELECT * FROM groupp WHERE groupName=?;";
	public static final String UPDATE = "UPDATE groupp SET version =? , groupName =? WHERE groupId =?;";
	public static final String DELETE = "DELETE FROM groupp WHERE groupId=?;";
	public static ResultSet findAllgroups() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return ps.executeQuery();
	}
	public static ResultSet findGroup(int groupId) throws SQLException {		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTID);
		ps.setInt(1, groupId);
		return ps.executeQuery();
	}
	public static ResultSet findGroup(String groupName) throws SQLException {		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTNAME);
		ps.setString(1, groupName);
		return ps.executeQuery();
	}
	public static void insertGroup(int version, String groupName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setInt(1, version);
		ps.setString(2, groupName);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void update(int version,String NewGroupName,int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setInt(1, version);
		ps.setString(2, NewGroupName);
		ps.setInt(3, groupId);
		ps.executeUpdate();
		ps.close();
	}
	public static void delete(int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setInt(1, groupId);
		ps.executeUpdate();
		ps.close();
	}
	
	
}

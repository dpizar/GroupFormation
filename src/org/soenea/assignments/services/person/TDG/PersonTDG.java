package org.soenea.assignments.services.person.TDG;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;


public class PersonTDG {
	
	public static final String INSERT = "INSERT INTO user (userName,version,password,firstName,lastName,groupId,userType) VALUES (?,?,?,?);";
	public static final String UPDATE = "UPDATE user SET groupId =? , version =? WHERE userName =?;";
	public static final String DELETE = "DELETE FROM user WHERE userName=? AND version=?;";
	public static final String DELETEPERSONGRP = "UPDATE user SET groupId =? , version =? WHERE groupId =?;";
	
	public static final String SELECT_ALL = "SELECT * FROM user;";
	public static final String SELECT = "SELECT * FROM user WHERE userName=?;";
	public static final String AUTHENTICATE = "SELECT * FROM user WHERE userName=? AND password=?;";
	public static final String SELECTGROUP = "SELECT groupId FROM user WHERE userName=?;";
	public static final String SELECTUSERNOGROUP = "SELECT * FROM user WHERE groupId=0;";
	public static final String SELECTGROUPUSER = "SELECT * FROM user WHERE groupId=?;";
	public static final String SELECTGROUPUSER2 = "SELECT * FROM user WHERE groupId=? AND userName!=?;";
	
	public static ResultSet authenticate(String userName, String password) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(AUTHENTICATE);
		ps.setString(1, userName);
		ps.setString(2, password);
		return ps.executeQuery();
	}
	
	public static ResultSet getGroupId(String userName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTGROUP);
		ps.setString(1, userName);
		return ps.executeQuery();
	}
	public static ResultSet FindGrpUser(int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTGROUPUSER);
		ps.setInt(1, groupId);
		return ps.executeQuery();
	}
	public static ResultSet FindGrpUser2(String userName,int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTGROUPUSER2);
		ps.setInt(1, groupId);
		ps.setString(2, userName);
		return ps.executeQuery();
	}
	public static ResultSet FindAllGroupIdZeroUser() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECTUSERNOGROUP);
		return ps.executeQuery();
	}
	public static ResultSet findUser(String userName) throws SQLException {		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setString(1, userName);
		return ps.executeQuery();
	}

	public static ResultSet findAllUsers() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return ps.executeQuery();
	}

	public static void insertUser(String userName, int version, String password, String firstName, String lastName, int groupId, int userType) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, userName);
		ps.setInt(2, version);
		ps.setString(3, password);
		ps.setString(4, firstName);
		ps.setString(5, lastName);
		ps.setInt(6, groupId);
		ps.setInt(7, userType);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void update(String userName, int version, String password, String firstName, String lastName, int groupId, int userType) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setInt(1, groupId);
		ps.setInt(2, version);
		ps.setString(3, userName);
		ps.executeUpdate();
		ps.close();
	}
	// out of our use case
	/*
	public static void updateUser(String userName, int version, String password, String firstName, String lastName, int groupId, int userType) {}
	*/
	public static void RemovePersonGroup(int version,int groupId) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETEPERSONGRP);
		ps.setInt(1, 0);
		ps.setInt(2, version);
		ps.setInt(3, groupId);
		ps.executeUpdate();
		ps.close();
	}
	public static void deleteUser(String userName, int version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setString(1, userName);
		ps.setInt(2, version);
		ps.executeUpdate();
		ps.close();
	}
	
	

}

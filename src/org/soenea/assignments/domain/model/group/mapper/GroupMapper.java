package org.soenea.assignments.domain.model.group.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.group;
import org.soenea.assignments.services.group.TDG.GroupTDG;

public class GroupMapper {
	public static ArrayList<group> findAll() throws SQLException{
		ArrayList<group> allGroups = new ArrayList<group>();
		ResultSet rs = GroupTDG.findAllgroups();
		while(rs.next()) {
			int gId=rs.getInt("groupId");
			int version=rs.getInt("version");
			String groupName=rs.getString("groupName");
			group u1 = new group(gId,version,groupName);
			allGroups.add(u1);
		}
		return allGroups;
	}
	public static group findGroup(int groupId) throws SQLException{
		if(GroupIM.get()!=null)
			if(GroupIM.has(groupId))
				GroupIM.get();
		ResultSet rs = GroupTDG.findGroup(groupId);
		group u1 = null;
		if(rs.next()){			
			String gName=rs.getString("groupName");
			int version=rs.getInt("version");
			u1 = new group(groupId,version,gName);
		}
		GroupIM.put(u1);
		return u1;
	}
	
	public static void update(group g1) throws SQLException {
		GroupTDG.update(g1.getversion(),g1.getgroupName(),g1.getgroupId1());
		GroupIM.put(g1);
	}
	
	public static group findGroup(String groupName) throws SQLException{
		if(GroupIM.get()!=null)
			if(GroupIM.has(groupName))
				GroupIM.get();
		ResultSet rs = GroupTDG.findGroup(groupName);
		group u1 = null;
		if(rs.next()){			
			int gId=rs.getInt("groupId");
			int version=rs.getInt("version");
			u1 = new group(gId,version,groupName);
		}
		GroupIM.put(u1);
		return u1;
	}
	
	public static void insertGroup(int version,String groupName) throws SQLException {
		GroupTDG.insertGroup(version,groupName);
	}
	
	public static void delete(IGroup currentgrp) throws SQLException {
		GroupTDG.delete(currentgrp.getgroupId1());
		if(GroupIM.has(currentgrp.getgroupId1()))
			GroupIM.put(null);
	}
	
}

package org.soenea.assignments.domain.model.group;

import java.sql.SQLException;

import org.soenea.assignments.domain.model.group.mapper.GroupMapper;

public class GroupProxy implements IGroup{
	private group realgroup = null;
	private int groupId;
	
	public GroupProxy(int groupId) {
		super();
		this.groupId = groupId;

	}
	public group getRealGroup()
	{
		if(realgroup==null)
			try {
				realgroup = GroupMapper.findGroup(groupId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return realgroup;
	}
	
	public int getgroupId1() {
		return getRealGroup().getgroupId1();
	}
	public int getversion() {
		return getRealGroup().getversion();
	}
	public String getgroupName() {
		return getRealGroup().getgroupName();
	}
	public void setgroupId1(int groupId) {
		getRealGroup().setgroupId1(groupId);
	}
	public void setversion(int version) {
		getRealGroup().setversion(version);
	}
	public void setgroupName(String groupName) {
		getRealGroup().setgroupName(groupName);
	}

}

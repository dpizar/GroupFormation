package org.soenea.assignments.domain.model.group;


public class group implements IGroup{

	private int groupId;
	private int version;
	private String groupName;
	public group(int groupId, int version, String groupName) {
		super();
		this.groupId = groupId;
		this.version = version;
		this.groupName = groupName;

	}
	public group() {
		// TODO Auto-generated constructor stub
	}
	public int getgroupId1() {
		return groupId;
	}
	public int getversion() {
		return version;
	}
	public String getgroupName() {
		return groupName;
	}
	public void setgroupId1(int groupId) {
		this.groupId = groupId;
	}
	public void setversion(int version) {
		this.version = version;
	}
	public void setgroupName(String groupName) {
		this.groupName = groupName;
	}
}

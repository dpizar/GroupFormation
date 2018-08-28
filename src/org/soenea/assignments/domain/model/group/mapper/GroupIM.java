package org.soenea.assignments.domain.model.group.mapper;
import org.soenea.assignments.domain.model.group.IGroup;
import org.soenea.assignments.domain.model.group.group;
public class GroupIM {
	
    public static IGroup groupIM = new group(0,0,null);

	public static boolean has(int groupId) {
		if(groupIM.getgroupId1()==groupId)
			return true;
		return false;
	}
	public static boolean has(String groupName) {
		if(groupIM.getgroupName()==groupName)
			return true;
		return false;
	}
	public static IGroup get ()
	{
		return groupIM;
	}
	public static void put(IGroup g1)
	{
		groupIM = g1;
	}
}

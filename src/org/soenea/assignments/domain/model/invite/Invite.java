package org.soenea.assignments.domain.model.invite;

import org.dsrg.soenea.domain.DomainObject;


public class Invite  extends DomainObject<Long> implements IInvite{
	
	private String userId;
	private long groupId;

	// Constructor
	public Invite( long id,int version, String userId, long groupId ) 
	{
		super(id, version);
		this.userId = userId;
		this.groupId = groupId;
	}
	
	// getters/setters
	public String getUserId()
	{
		return userId;
	}
	
	public long getGroupId()
	{
		return groupId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setGroupId(long groupId)
	{
		this.groupId = groupId;
	}

}

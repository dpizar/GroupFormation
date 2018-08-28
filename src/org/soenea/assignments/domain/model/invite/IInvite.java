package org.soenea.assignments.domain.model.invite;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IInvite extends IDomainObject<Long>{

	public String getUserId();
	
	public long getGroupId();
	
	public void setUserId(String userId);
	
	public void setGroupId(long groupId);
}
